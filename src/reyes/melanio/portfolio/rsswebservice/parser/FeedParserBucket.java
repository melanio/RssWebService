package reyes.melanio.portfolio.rsswebservice.parser;

import org.jdom.Document;

public class FeedParserBucket {
	private static IFeedParser[] parsers;

	private FeedParserBucket() {

	}

	public void setParsers(IFeedParser[] parsers) {
		FeedParserBucket.parsers = parsers;
	}

	public IFeedParser getParser(Document document) {
		IFeedParser result = null;
		for (IFeedParser parser : parsers) {
			if (parser.checkForamt(document)) {
				result = parser;
				break;
			}
		}
		return result;
	}

}
