package de.hshannover.f4.trust.irongui.communication;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import util.DomHelpers;
import de.hshannover.f4.trust.ifmapj.exception.IfmapErrorResult;
import de.hshannover.f4.trust.ifmapj.exception.MarshalException;
import de.hshannover.f4.trust.ifmapj.exception.UnmarshalException;
import de.hshannover.f4.trust.ifmapj.identifier.Identifier;
import de.hshannover.f4.trust.ifmapj.identifier.Identifiers;
import de.hshannover.f4.trust.ifmapj.messages.Request;
import de.hshannover.f4.trust.ifmapj.messages.RequestHandler;
import de.hshannover.f4.trust.ifmapj.messages.RequestImpl;
import de.hshannover.f4.trust.ifmapj.messages.Requests.Helpers;
import de.hshannover.f4.trust.ifmapj.messages.Result;

class DumpRequestHandler implements RequestHandler<DumpRequest> {
	
	DumpRequestHandler() { }
	
	public static final String DUMP_REQ_EL_NAME = "dump";
	public static final String DUMP_REQ_IDENT_FILTER = "identifier";
	public static final String DUMP_RES_EL_NAME = "dumpResult";
	public static final String DUMP_RES_LAST_UPDATE_ATTRIB = "last-update";


	@Override
	public Element toElement(Request req, Document doc) throws MarshalException {
		
		Helpers.checkRequestType(req, this);
		String identFilter = ((DumpRequest)req).getIdentifierFilter();
		
		Element ret = doc.createElementNS(Helpers.baseNsUri(),
				DomHelpers.makeRequestFQName(DUMP_REQ_EL_NAME));
		
		if (identFilter  != null)
			DomHelpers.addAttribute(ret, DUMP_REQ_IDENT_FILTER, identFilter);
		
		Helpers.addSessionId(ret, req);
		
		return ret;
	}

	@Override
	public Result fromElement(Element res) throws UnmarshalException,
			IfmapErrorResult {
		
		Element content = Helpers.getResponseContentErrorCheck(res);
		Attr lastUpdateNode = null;
		String lastUpdate = null;
		List<Element> children;
		List<Identifier> resIdents = new ArrayList<Identifier>();

		if (!DomHelpers.elementMatches(content, DUMP_RES_EL_NAME))
			throw new UnmarshalException("No dumpResult element found");
			

		lastUpdateNode = content.getAttributeNode(DUMP_RES_LAST_UPDATE_ATTRIB);
		
		if (lastUpdateNode == null)
			throw new UnmarshalException("No " + DUMP_RES_LAST_UPDATE_ATTRIB +
					" attribute in " + DUMP_RES_EL_NAME + " element found");
		
		lastUpdate = content.getAttribute(DUMP_RES_LAST_UPDATE_ATTRIB);
		
		children = DomHelpers.getChildElements(content);
		
		for (Element child : children)
			resIdents.add((Identifiers.fromElement(child)));
		
		return new DumpResultImpl(lastUpdate, resIdents);
	}

	@Override
	public Class<DumpRequest> handles() {
		return DumpRequest.class;
	}
}

class DumpRequestImpl extends RequestImpl implements DumpRequest {
	
	private String mIdentifierFilter;
	
	DumpRequestImpl(String identFilter) {
		mIdentifierFilter = identFilter;
	}

	DumpRequestImpl() {
		this(null);
	}

	@Override
	public void setIdentifierFilter(String filter) {
		mIdentifierFilter = filter;
	}

	@Override
	public String getIdentifierFilter() {
		return mIdentifierFilter;
	}
}

class DumpResultImpl implements DumpResult {
	
	private final String mLastUpdate;
	
	private final Collection<Identifier> mIdentifiers;
	
	DumpResultImpl(String lastUpdate, Collection<Identifier> identifiers) {
		
		if (identifiers == null)
			throw new NullPointerException("identifiers list is null");
		
		if (lastUpdate == null)
			throw new NullPointerException("lastUpdate is null");

		mLastUpdate = lastUpdate;
		mIdentifiers = identifiers;
	}

	@Override
	public String getLastUpdate() {
		return mLastUpdate;
	}

	@Override
	public Collection<Identifier> getIdentifiers() {
		return Collections.unmodifiableCollection(mIdentifiers);
	}
}
