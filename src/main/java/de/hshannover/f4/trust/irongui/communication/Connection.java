/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of irongui, version 0.4.8,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2015 Trust@HsH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package de.hshannover.f4.trust.irongui.communication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import de.hshannover.f4.trust.ifmapj.exception.IfmapErrorResult;
import de.hshannover.f4.trust.ifmapj.exception.IfmapException;
import de.hshannover.f4.trust.ifmapj.identifier.Identifier;
import de.hshannover.f4.trust.ifmapj.messages.PollResult;
import de.hshannover.f4.trust.irongui.control.IfmapFacade;
import de.hshannover.f4.trust.irongui.datastructure.IdentifierData;
import de.hshannover.f4.trust.irongui.datastructure.PollResultContainer;
import de.hshannover.f4.trust.irongui.datastructure.SubscriptionRepository;
import de.hshannover.f4.trust.irongui.util.CryptoUtil;

public class Connection {

	private IfmapCommunication mIfmap;
	private ConnectionParameter mParameter;
	private Semaphore mSemaphore0 = null;
	private Semaphore mSemaphore1 = null;
	private Poller mPollThread;
	private Dumper mDumpThread;
	private Connection mSelf;
	private ExecutorService mExecuterService;
	private Future<Object> fDump;
	private Future<Object> fPoll;

	Connection(ConnectionParameter params) {
		mSelf = this;
		mParameter = cloneConnectionParameter(params);
		mPollThread = new Poller();
		mDumpThread = new Dumper();
		mSemaphore0 = new Semaphore(1);
		mSemaphore1 = new Semaphore(1);
	}

	public String getName() {
		return mParameter.getName();
	}

	public String getEndpoint() {
		return mParameter.getEndpoint();
	}

	public boolean isConnected() {
		if (mIfmap != null && mIfmap.getSessionId() != null) {
			return true;
		}
		return false;
	}

	public String getPublisherId() {
		return mIfmap.getPublisherId();
	}

	public String getSessionId() {
		return mIfmap.getSessionId();
	}

	void setConnectionParameter(ConnectionParameter params) {
		mParameter = params;
	}

	public ConnectionParameter getConnectionParameters() {
		return cloneConnectionParameter(mParameter);
	}

	public void disconnect() throws IfmapErrorResult, IfmapException,
			InterruptedException {
		if (this.mIfmap != null) {
			this.enableDumping(false);
			this.enablePolling(false);
			this.mIfmap.endSession();
			mExecuterService.shutdownNow();
		}
	}

	public void brokenDisconnect() {
		if (this.mIfmap != null) {
			mDumpThread.mDone = true;
			mPollThread.mDone = true;
			this.enableDumping(false);
			this.enablePolling(false);
		}
	}

	public void connect() throws FileNotFoundException, IOException,
			IfmapErrorResult, IfmapException, InterruptedException {
		if (mIfmap == null) {
			mIfmap = new IfmapCommunication(mParameter.getEndpoint(),
					mParameter.getKeystore(), mParameter.getKeystorePass(),
					mParameter.getTruststore(), mParameter.getTruststorePass(),
					mParameter.isBasicAuthEnabled(),
					mParameter.getBasicauthUser(),
					mParameter.getBasicauthPass(), mParameter.getMaxPollSize());
		}
		mExecuterService = Executors.newCachedThreadPool();
		mIfmap.newSession();
		enablePolling(true);
	}

	public String subscribeUpdate(Identifier identifier, Integer depth,
			Integer size, String filter, String links, String[] terminal) {
		String suid = null;
		try {
			mSemaphore0.acquire();
			if (!isConnected()) {
				connect();
				enablePolling(true);
			}
			suid = mIfmap.subscribeUpdate(identifier, depth, size, filter,
					links, terminal);
		} catch (Exception e) {
			mSemaphore0.release();
		}
		mSemaphore0.release();
		return suid;
	}

	public void subscribeDelete(String[] keys) {
		try {
			mSemaphore0.acquire();
			if (isConnected()) {
				mIfmap.subscribeDelete(keys);
			}
			mSemaphore0.release();
		} catch (Exception e) {
			mSemaphore0.release();
		}
	}

	public void subscribeDelete(Set<String> keys) {
		try {
			mSemaphore0.acquire();
			if (isConnected()) {
				mIfmap.subscribeDelete(keys);
			}
			mSemaphore0.release();
		} catch (Exception e) {
			mSemaphore0.release();
		}
	}

	public void enablePolling(boolean poll) {
		if (poll) {
			mPollThread = new Poller();
			fPoll = mExecuterService.submit(mPollThread);
		} else {
			mPollThread.mDone = true;
			if (fPoll != null) {
				fPoll.cancel(true);
			}
		}
	}

	public void enableDumping(boolean dump) {
		if (dump) {
			mDumpThread = new Dumper();
			fDump = mExecuterService.submit(mDumpThread);
		} else {
			mDumpThread.mDone = true;
			if (fDump != null) {
				fDump.cancel(true);
			}
		}
	}

	private ConnectionParameter cloneConnectionParameter(ConnectionParameter tmp) {
		ConnectionParameter params = new ConnectionParameter();
		params.setName(tmp.getName());
		params.setEndpoint(tmp.getEndpoint());
		params.setKeystore(tmp.getKeystore());
		params.setKeystorePass(tmp.getKeystorePass());
		params.setTruststore(tmp.getTruststore());
		params.setTruststorePass(tmp.getTruststorePass());
		params.setBasicAuthEnabled(tmp.isBasicAuthEnabled());
		params.setBasicauthUser(tmp.getBasicauthUser());
		params.setBasicauthPass(tmp.getBasicauthPass());
		params.setDump(tmp.isDump());
		params.setAutoConnect(tmp.isAutoConnect());
		params.setMaxPollSize(tmp.getMaxPollSize());
		return params;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!(o instanceof Connection)) {
			return false;
		}
		if (this.hashCode() == o.hashCode()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return mParameter.getName().hashCode();
	}

	@Override
	public String toString() {
		return mParameter.getName();
	}

	private class Poller implements Callable<Object> {
		public volatile boolean mDone = false;

		@Override
		public Object call() throws Exception {
			while (!Thread.currentThread().isInterrupted() && !mDone) {
				try {
					mSemaphore1.acquire();
					PollResult pr = mIfmap.poll();
					PollResultContainer poll = IfmapMarshaller
							.marshallPollResult(pr);
					poll = IfmapMarshaller.filterPollResult(mSelf, poll);
					IfmapFacade.notifyRepositoryChanged(mSelf, poll);
					mSemaphore1.release();
				} catch (Exception e) {
					mDone = true;
					IfmapFacade.notifyConnectionBroken(mSelf, e);
					mSemaphore1.release();
				}
			}

			mDone = true;
			mSemaphore1.release();
			return null;
		}
	}

	private class Dumper implements Callable<Object> {
		public volatile boolean mDone = false;

		private List<IdentifierData> transformResult(
				Collection<Identifier> idents) {
			ArrayList<IdentifierData> l = new ArrayList<IdentifierData>();
			Iterator<Identifier> itr = idents.iterator();
			while (itr.hasNext()) {
				Identifier ident = itr.next();
				IdentifierData identData = new IdentifierData(ident);
				String uuid = CryptoUtil
						.generateMd5BySize(ident.toString(), 16);
				if (!SubscriptionRepository.getInstance().isAlreadySubscribed(
						mSelf, identData)) {
					identData.setSubscriptionName(uuid);
					l.add(identData);
				}

			}
			return l;
		}

		@Override
		public Object call() throws Exception {
			while (!Thread.currentThread().isInterrupted() && !mDone) {
				long time = 0;
				try {
					mSemaphore0.acquire();
					DumpResult dump = mIfmap.dump(null);
					long lastUpdate = Long.parseLong(dump.getLastUpdate());
					if (lastUpdate > time) {
						time = lastUpdate;
						Collection<Identifier> identifier = dump
								.getIdentifiers();
						Collection<Identifier> cTemp = null;
						if (identifier != null && !identifier.isEmpty()) {
							cTemp = new ArrayList<Identifier>();
							List<IdentifierData> result = transformResult(identifier);
							if (!result.isEmpty()) {
								Iterator<IdentifierData> itr = result
										.iterator();
								while (itr.hasNext()) {
									IdentifierData id = itr.next();
									SubscriptionRepository.getInstance()
											.addSubscription(mSelf, id);
									cTemp.add(id.getRequestObject());
								}
								// List<String> uuids =
								mIfmap.subscribeUpdate(cTemp, 10, null, null,
										null, null);
							}
						}
					}
					/*
					 * mEventService
					 * .notifyStatusChangedReceiver("Last update: " +
					 * DateUtil.getFormattedDateFromTimestamp(lastUpdate)); }
					 */
					mSemaphore0.release();
					Thread.sleep(10000);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Dump stopped (Exception occured)");
					mSemaphore0.release();
					throw e;
				}
			}
			System.err.println("Dump ended.");
			return null;
		}

	}
}
