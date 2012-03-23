package reyes.melanio.portfolio.rsswebservice.parser;

import java.util.List;

import org.jdom.Document;

import reyes.melanio.portfolio.rsswebservice.data.FeedItem;
import reyes.melanio.portfolio.rsswebservice.util.DateParser;

public interface IFeedParser {

	public String getFormat();

	public boolean checkForamt(Document document);

	public List<FeedItem> parse(Document document);

	public void setDateParser(DateParser dateParser);
}
