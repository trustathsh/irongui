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
 * This file is part of irongui, version 0.4.7,
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

import de.hshannover.f4.trust.ifmapj.IfmapJ;
import de.hshannover.f4.trust.ifmapj.binding.IfmapStrings;
import de.hshannover.f4.trust.ifmapj.channel.ARC;
import de.hshannover.f4.trust.ifmapj.channel.SSRC;
import de.hshannover.f4.trust.ifmapj.config.BasicAuthConfig;
import de.hshannover.f4.trust.ifmapj.config.CertAuthConfig;
import de.hshannover.f4.trust.ifmapj.exception.EndSessionException;
import de.hshannover.f4.trust.ifmapj.exception.IfmapErrorResult;
import de.hshannover.f4.trust.ifmapj.exception.IfmapException;
import de.hshannover.f4.trust.ifmapj.exception.InitializationException;
import de.hshannover.f4.trust.ifmapj.identifier.Identifier;
import de.hshannover.f4.trust.ifmapj.messages.PollResult;
import de.hshannover.f4.trust.ifmapj.messages.Requests;
import de.hshannover.f4.trust.ifmapj.messages.Result;
import de.hshannover.f4.trust.ifmapj.messages.SubscribeDelete;
import de.hshannover.f4.trust.ifmapj.messages.SubscribeRequest;
import de.hshannover.f4.trust.ifmapj.messages.SubscribeUpdate;
import de.hshannover.f4.trust.irongui.util.CryptoUtil;

public class IfmapCommunication {

	static {
		Requests.registerRequestHandler(new DumpRequestHandler());
	}
	private SSRC mSsrc = null;
	private ARC mArc = null;

	private volatile String mIfmapPublisherId = null;
	private volatile String mSessionId = null;
	private Semaphore mSemaphore;
	private int mMaxPoll = 0;

	IfmapCommunication(String url, String keyPath, String keyPass,
			String trustPath, String trustPass, boolean enableBasicAuth,
			String basicUser, String basicPass, int mp)
			throws InitializationException {
		if (enableBasicAuth) {
			BasicAuthConfig basicConfig = new BasicAuthConfig(url, basicUser,
					basicPass, trustPath, trustPass);
			mSsrc = IfmapJ.createSsrc(basicConfig);
		} else {
			CertAuthConfig certConfig = new CertAuthConfig(url, trustPath,
					trustPass, trustPath, trustPass);
			mSsrc = IfmapJ.createSsrc(certConfig);
		}
		mSemaphore = new Semaphore(1);
		mMaxPoll = mp;
	}

	String getPublisherId() {
		return mIfmapPublisherId;
	}

	String getSessionId() {
		return mSessionId;
	}

	void newSession() throws IfmapErrorResult, IfmapException,
			InterruptedException {
		// mSemaphore.acquire();
		if (mMaxPoll == 0) {
			mSsrc.newSession();
		} else {
			mSsrc.newSession(mMaxPoll);
		}
		mSessionId = mSsrc.getSessionId();
		mIfmapPublisherId = mSsrc.getPublisherId();
		// mSemaphore.release();
	}

	void subscribeDelete(String[] keys) {
		if (keys != null && keys.length > 0) {
			SubscribeRequest req = Requests.createSubscribeReq();
			for (String uuid : keys) {
				SubscribeDelete delete = Requests.createSubscribeDelete(uuid);
				req.addSubscribeElement(delete);
			}
			if (mSessionId != null) {
				try {
					mSsrc.subscribe(req);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	void subscribeDelete(Set<String> keys) {
		if (keys != null && !keys.isEmpty()) {
			SubscribeRequest req = Requests.createSubscribeReq();
			for (String uuid : keys) {
				SubscribeDelete delete = Requests.createSubscribeDelete(uuid);
				req.addSubscribeElement(delete);
			}
			if (mSessionId != null) {
				try {
					mSsrc.subscribe(req);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	List<String> subscribeUpdate(Collection<Identifier> idents, Integer depth,
			Integer size, String filter, String links, String[] terminal) {
		if (idents != null && !idents.isEmpty()) {
			Iterator<Identifier> iter = idents.iterator();
			SubscribeRequest req = Requests.createSubscribeReq();
			ArrayList<String> uuids = new ArrayList<String>();
			while (iter.hasNext()) {
				SubscribeUpdate update = Requests.createSubscribeUpdate();
				Identifier ident = iter.next();
				update.setStartIdentifier(ident);
				String uuid = CryptoUtil
						.generateMd5BySize(ident.toString(), 16);
				update.setName(uuid);
				uuids.add(uuid);

				if (depth != null && depth > 0) {
					update.setMaxDepth(depth);
				}
				if (size != null && size > 0){
					update.setMaxSize(size);
				}
				if (filter != null && !filter.trim().isEmpty()){
					update.setResultFilter(filter);
				}
				if (links != null && !links.trim().isEmpty()){
					update.setMatchLinksFilter(links);
				}

				if (terminal != null && terminal.length > 0) {
					StringBuffer sb = new StringBuffer();
					int i = 0;
					for (; i < terminal.length - 1; i++) {
						if (!terminal[i].trim().isEmpty()) {
							sb.append(terminal[i]);
							sb.append(',');
						}
					}
					if (!terminal[i].trim().isEmpty()) {
						sb.append(terminal[i]);
					}

					String terminalString = sb.toString();

					if (!terminalString.trim().isEmpty()) {
						update.setTerminalIdentifierTypes(terminalString);
					}
				}
				req.addSubscribeElement(update);
			}
			try {
				mSsrc.subscribe(req);
			} catch (IfmapErrorResult e) {
				System.err.println(e.getErrorString());
				e.printStackTrace();
				return null;
			} catch (IfmapException e) {
				e.printStackTrace();
				return null;
			}
			return uuids;

		}
		return null;
	}

	String subscribeUpdate(Identifier identifier, Integer depth, Integer size,
			String filter, String links, String[] terminal)
			throws InterruptedException {
		SubscribeUpdate update = Requests.createSubscribeUpdate();
		update.setStartIdentifier(identifier);
		String uuid = CryptoUtil.generateMd5BySize(identifier.toString(), 16);
		update.setName(uuid);

		if (depth != null && depth > 0) {
			update.setMaxDepth(depth);
		}
		if (size != null && size > 0) {
			update.setMaxSize(size);
		}
		if (filter != null && !filter.trim().isEmpty()) {
			update.setResultFilter(filter);
		}
		if (links != null && !links.trim().isEmpty()) {
			update.setMatchLinksFilter(links);
		}

		// FIXME: hack to add meta prefix, no matter what ...
		update.addNamespaceDeclaration(IfmapStrings.STD_METADATA_PREFIX,
				IfmapStrings.STD_METADATA_NS_URI);

		if (terminal != null && terminal.length > 0) {
			StringBuffer sb = new StringBuffer();
			int i = 0;
			for (; i < terminal.length - 1; i++) {
				if (!terminal[i].trim().isEmpty()) {
					sb.append(terminal[i]);
					sb.append(',');
				}
			}
			if (!terminal[i].trim().isEmpty()) {
				sb.append(terminal[i]);
			}

			String terminalString = sb.toString();

			if (!terminalString.trim().isEmpty()) {
				update.setTerminalIdentifierTypes(terminalString);
			}
		}

		SubscribeRequest req = Requests.createSubscribeReq(update);
		// for(SubscribeElement element : req.getSubscribeElements() ){
		// System.err.println(element.toString());
		// }
		try {
			if (mSessionId == null) {
				this.newSession();
			}
			mSsrc.subscribe(req);
		} catch (IfmapErrorResult e) {
			System.err.println(e.getErrorString());
			e.printStackTrace();
			return null;
		} catch (IfmapException e) {
			e.printStackTrace();
			return null;
		}
		return uuid;

	}

	/**
	 * This Method ends the Session with the IF-MAP Server
	 * 
	 * @return true - if endSession was successful, false - otherwise
	 * @throws IfmapException
	 * @throws IfmapErrorResult
	 * @throws InterruptedException
	 * @throws RemoteException
	 */
	void endSession() {
		if (mSsrc != null) {
			try {
				mSemaphore.acquire();
				mSsrc.endSession();
				mSsrc.closeTcpConnection();
				this.mArc.closeTcpConnection();
				this.mArc = null;
				mIfmapPublisherId = null;
				mSessionId = null;
				mSemaphore.release();
			} catch (InterruptedException e) {
			} catch (IfmapErrorResult e) {
				mIfmapPublisherId = null;
				mSessionId = null;
				mSemaphore.release();
			} catch (IfmapException e) {
				mIfmapPublisherId = null;
				mSessionId = null;
				mSemaphore.release();
			}
		}
	}

	public PollResult poll() throws IfmapErrorResult, EndSessionException,
			IfmapException {
		if (this.mArc == null) {
			mArc = mSsrc.getArc();
		}
		return this.mArc.poll();
	}

	public DumpResult dump(String filter) throws IfmapErrorResult,
			IfmapException, InterruptedException {

		Result res;
		DumpRequest dreq = new DumpRequestImpl(filter);

		// if SSRC not there, do no request!
		if (this.mSsrc == null) {
			return null;
		}

		if (mSessionId == null) {
			newSession();
		}

		mSemaphore.acquire();

		res = this.mSsrc.genericRequestWithSessionId(dreq);

		mSemaphore.release();

		// If we don't get back a DumpResult instance something is messed up...
		if (!(res instanceof DumpResult)) {
			throw new IfmapException("DumpRequestHandler:",
					"dumpRequest didn't result in dumpResult");
		}

		return (DumpResult) res;
	}
}
