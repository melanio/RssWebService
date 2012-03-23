package reyes.melanio.portfolio.rsswebservice.tests;

import junit.framework.TestCase;
import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao;

public class FeedDaoTest extends TestCase {

	public void testCreateAndRetrieveFeed() {
		Feed feed = null;
		Long feedId = 0l;
		try {
			feed = new Feed("http://www.createFeedURL.com");
			feedId = FeedDao.insertFeed(feed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Feed retrievedFeed = FeedDao.findById(feedId);
		assertEquals(feed.getId(), retrievedFeed.getId());
		assertEquals(feed.getURL(), retrievedFeed.getURL());
		assertNotSame(feed, retrievedFeed);
	}

	public void testDeleteFeed() {
		Feed deleteFeed = null;
		try {
			deleteFeed = new Feed("http://www.deleteFeedURL.com");
			FeedDao.insertFeed(deleteFeed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FeedDao.deleteFeed(deleteFeed);
		} catch (RssWebServiceException e) {
			e.printStackTrace();
		}
		Feed retrievedFeed = FeedDao.findById(deleteFeed.getId());
		assertNull(retrievedFeed);
	}

	public void testUpdateFeed() {
		Feed updateFeed = null;
		Feed retrievedFeed = null;
		String url1 = "http://www.updateFeedURL1.com";
		String url2 = "http://www.updateFeedURL2.com";
		Long updateFeedId = 0l;
		try {
			updateFeed = new Feed(url1);
			updateFeedId = FeedDao.insertFeed(updateFeed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		retrievedFeed = FeedDao.findById(updateFeedId);
		assertEquals(updateFeed.getId(), retrievedFeed.getId());
		assertEquals(updateFeed.getURL(), retrievedFeed.getURL());

		try {
			updateFeed.setURL(url2);
			FeedDao.updateFeed(updateFeed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		retrievedFeed = FeedDao.findById(updateFeedId);
		assertEquals(updateFeed.getId(), retrievedFeed.getId());
		assertEquals(updateFeed.getURL(), retrievedFeed.getURL());
	}
}
