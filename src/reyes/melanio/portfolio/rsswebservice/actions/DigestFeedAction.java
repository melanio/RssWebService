package reyes.melanio.portfolio.rsswebservice.actions;

import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.findById;
import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedItemDao.saveFeedItems;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.DefaultProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.Protocol;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;

import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.data.FeedItem;
import reyes.melanio.portfolio.rsswebservice.error.Errors;
import reyes.melanio.portfolio.rsswebservice.parser.FeedParserBucket;
import reyes.melanio.portfolio.rsswebservice.parser.IFeedParser;
import reyes.melanio.portfolio.rsswebservice.util.SpringUtil;

public class DigestFeedAction extends AbstractAction {

	private Feed feed;

	public DigestFeedAction(long feedId) {
		super();
		this.feed = findById(feedId);
	}

	private Document getDocument() {
		Document document = null;
		try {
			HttpClient httpClient = new HttpClient();
			URI uri = feed.getURL().toURI();
			if ("feed".equals(uri.getScheme())) {
				Protocol feedProtocol = new Protocol("feed",
						new DefaultProtocolSocketFactory(), 80);
				Protocol.registerProtocol("feed", feedProtocol);
				httpClient.getHostConfiguration().setHost(uri.getHost(), 80,
						feedProtocol);
			}

			HttpMethod getMethod = new GetMethod(uri.toString());
			int responseCode = httpClient.executeMethod(getMethod);

			if (responseCode != 404) {
				InputStream inputStream = getMethod.getResponseBodyAsStream();
				BufferedReader bufferedInStream = new BufferedReader(
						new InputStreamReader(inputStream));
				SAXBuilder saxBuilder = new SAXBuilder();
				document = saxBuilder.build(bufferedInStream);
			}

		} catch (Exception e) {
			setRssWebServiceException(new RssWebServiceException(
					Errors.FAILED_TO_RETRIEVE_FEEDITEMS,
					Errors.FAILED_TO_RETRIEVE_FEEDITEMS_TEXT, e));
		}
		return document;
	}

	@Override
	public void execute() {
		if (checkFeed(feed)) {
			Document document = getDocument();
			FeedParserBucket feedParserBucket = SpringUtil.getParserBucket();
			IFeedParser parser = feedParserBucket.getParser(document);
			if (checkParser(parser)) {
				ArrayList<FeedItem> feedItems = (ArrayList<FeedItem>) parser
						.parse(document);
				try {
					saveFeedItems(feedItems, feed);
				} catch (RssWebServiceException e) {
					setRssWebServiceException(e);
				}
			}
		}
		return;
	}

	private boolean checkParser(IFeedParser parser) {
		boolean result = true;
		if (parser == null) {
			result = false;
			setRssWebServiceException(new RssWebServiceException(
					Errors.NO_SUITABLE_PARSER_FOUND,
					Errors.NO_SUITABLE_PARSER_FOUND_TEXT, null));
		}
		return result;
	}
}
