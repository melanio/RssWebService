package reyes.melanio.portfolio.rsswebservice.tests;

import java.text.DateFormat;
import java.util.Date;

import junit.framework.TestCase;
import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.data.FeedItem;
import reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao;
import reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedItemDao;

public class FeedItemDaoTest extends TestCase {

	private String testTitle = "This Is A FeedItem Test";
	private String testLink = "http://FeeditemTestLink.com/feeditem";
	private String testContent = "This is the test content for a FeedItem";
	private Feed feed;

	@Override
	public void setUp() {
		try {
			feed = new Feed("http://www.createFeedURLForFeedItemTest.com");
			FeedDao.insertFeed(feed);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCreateAndRetrieveFeedItemById() {
		FeedItem feedItem = null;
		Long feedItemId = 0l;
		try {
			feedItem = new FeedItem(feed);
			feedItem.setTitle(testTitle);
			feedItem.setLink(testLink);
			feedItem.setContent(testContent);
			feedItem.setPubDate(new Date());
			feedItemId = FeedItemDao.insertFeedItem(feedItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FeedItem retrievedFeedItem = FeedItemDao.findById(feedItemId);
		assertEquals(feedItem.getId(), retrievedFeedItem.getId());
		assertEquals(feedItem.getTitle(), retrievedFeedItem.getTitle());
		assertEquals(feedItem.getContent(), retrievedFeedItem.getContent());
		assertEquals(feedItem.getLink(), retrievedFeedItem.getLink());
		assertEquals(
				DateFormat.getDateTimeInstance().format(feedItem.getPubDate()),
				DateFormat.getDateTimeInstance().format(
						retrievedFeedItem.getPubDate()));
		assertEquals(feedItem.toString(), retrievedFeedItem.toString());

		feed = FeedDao.findById(feed.getId());
		assertTrue(!feed.getFeedItems().isEmpty());
	}

	public void testDeleteFeedItem() {
		FeedItem deleteFeedItem = null;
		Long deleteFeedItemId = 0l;
		try {
			deleteFeedItem = new FeedItem();
			String appendString = "Delete";
			deleteFeedItem.setTitle(testTitle + appendString);
			deleteFeedItem.setLink(testLink + appendString);
			deleteFeedItem.setContent(testContent + appendString);
			deleteFeedItem.setPubDate(new Date());
			deleteFeedItem.setFeed(feed);
			deleteFeedItemId = FeedItemDao.insertFeedItem(deleteFeedItem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FeedItemDao.deleteFeedItem(deleteFeedItem);
		} catch (RssWebServiceException e) {
			e.printStackTrace();
		}
		FeedItem retrievedFeed = FeedItemDao.findById(deleteFeedItemId);
		assertNull(retrievedFeed);
	}

	public void testUpdateFeedItem() {
		FeedItem updateFeedItem = null;
		FeedItem retrievedFeedItem = null;
		String appendString = "Update";
		Long updateFeedItemId = 0l;
		try {
			updateFeedItem = new FeedItem();
			updateFeedItem.setTitle(testTitle);
			updateFeedItem.setLink(testLink);
			updateFeedItem.setContent(testContent);
			updateFeedItem.setPubDate(new Date());
			updateFeedItem.setFeed(feed);
			updateFeedItemId = FeedItemDao.insertFeedItem(updateFeedItem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		retrievedFeedItem = FeedItemDao.findById(updateFeedItemId);
		assertEquals(updateFeedItem.getId(), retrievedFeedItem.getId());
		assertEquals(updateFeedItem.getTitle(), retrievedFeedItem.getTitle());
		assertEquals(updateFeedItem.getContent(),
				retrievedFeedItem.getContent());
		assertEquals(
				DateFormat.getDateTimeInstance().format(
						updateFeedItem.getPubDate()),
				DateFormat.getDateTimeInstance().format(
						retrievedFeedItem.getPubDate()));
		assertEquals(updateFeedItem.toString(), retrievedFeedItem.toString());

		try {
			updateFeedItem.setTitle(testTitle + appendString);
			updateFeedItem.setLink(testLink + appendString);
			updateFeedItem.setContent(testContent + appendString);
			updateFeedItem.setPubDate(new Date());
			updateFeedItemId = FeedItemDao.insertFeedItem(updateFeedItem);
		} catch (Exception e) {
			e.printStackTrace();
		}

		retrievedFeedItem = FeedItemDao.findById(updateFeedItemId);
		assertEquals(updateFeedItem.getId(), retrievedFeedItem.getId());
		assertEquals(updateFeedItem.getTitle(), retrievedFeedItem.getTitle());
		assertEquals(updateFeedItem.getContent(),
				retrievedFeedItem.getContent());
		assertEquals(
				DateFormat.getDateTimeInstance().format(
						updateFeedItem.getPubDate()),
				DateFormat.getDateTimeInstance().format(
						retrievedFeedItem.getPubDate()));
		assertEquals(updateFeedItem.toString(), retrievedFeedItem.toString());
	}
}
