package reyes.melanio.portfolio.rsswebservice.data.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.data.FeedItem;
import reyes.melanio.portfolio.rsswebservice.error.Errors;

public class FeedItemDao {
	private static Logger logger = Logger.getLogger(FeedItemDao.class);

	public FeedItemDao() {

	}

	private static Session getSession() {
		return SessionFactoryUtil.instance().getCurrentSession();
	}

	public static Long insertFeedItem(FeedItem feedItem)
			throws RssWebServiceException {
		Transaction trans = null;
		Session session = getSession();

		try {
			trans = session.beginTransaction();
			session.save(feedItem);
			trans.commit();

		} catch (RuntimeException e) {
			if (trans != null && trans.isActive()) {
				try {
					logger.error(
							"Error interting FeedItem, rolling back transaction",
							e);
					trans.rollback();
				} catch (HibernateException he) {
					logger.error("Error rolling back transaction", he);
				}
			}
			e.printStackTrace();
			throw new RssWebServiceException(Errors.DATABASE_INSERT_ERROR,
					Errors.DATABASE_INSERT_ERROR_TEXT, e);

		}
		return feedItem.getId();
	}

	public static void deleteFeedItem(FeedItem feedItem)
			throws RssWebServiceException {
		Transaction trans = null;
		Session session = getSession();
		try {
			trans = session.beginTransaction();
			session.delete(feedItem);
			trans.commit();

		} catch (RuntimeException e) {
			if (trans != null && trans.isActive()) {
				try {
					logger.error(
							"Error deleting FeedItem, rolling back transaction",
							e);
					trans.rollback();
				} catch (HibernateException he) {
					logger.error("Error rolling back transaction", he);
				}
			}
			throw new RssWebServiceException(Errors.DATABASE_DELETE_ERROR,
					Errors.DATABASE_DELETE_ERROR_TEXT, e);
		}
	}

	public static void updateFeedItem(FeedItem feedItem)
			throws RssWebServiceException {
		Transaction trans = null;
		Session session = getSession();
		try {
			trans = session.beginTransaction();
			FeedItem retrievedFeedItem = findById(feedItem.getId());
			retrievedFeedItem.setTitle(feedItem.getTitle());
			retrievedFeedItem.setContent(feedItem.getContent());
			retrievedFeedItem.setPubDate(feedItem.getPubDate());
			session.update(retrievedFeedItem);
			trans.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trans != null && trans.isActive()) {
				try {

					logger.error(
							"Error updating FeedItem rolling back transaction",
							e);
					trans.rollback();
				} catch (HibernateException he) {
					logger.error("Error rolling back transaction", he);
				}
			}
			throw new RssWebServiceException(Errors.DATABASE_UPDATE_ERROR,
					Errors.DATABASE_UPDATE_ERROR_TEXT, e);
		}
	}

	public static FeedItem findById(Long id) {
		FeedItem feedItem = null;
		Session session = getSession();
		session.beginTransaction();
		feedItem = (FeedItem) session.get(FeedItem.class, id);
		return feedItem;
	}

	public static FeedItem[] findByFeedId(Long feedId) {
		Session session = getSession();
		session.beginTransaction();
		List<?> results = session
				.createQuery("from FeedItem where feedId = :id")
				.setParameter("id", feedId).list();
		return (FeedItem[]) results.toArray(new FeedItem[0]);
	}

	public static FeedItem[] findByFeedIdAndDateRange(Long feedId,
			Date startDate, Date endDate) {
		Session session = getSession();
		session.beginTransaction();
		List<?> results = session.createCriteria(FeedItem.class)
				.add(Restrictions.ge("pubDate", startDate))
				.add(Restrictions.lt("pubDate", endDate)).list();
		return (FeedItem[]) results.toArray(new FeedItem[0]);
	}

	public static FeedItem[] findByFeedIdAndStartDate(Long feedId,
			Date startDate) {
		Session session = getSession();
		session.beginTransaction();
		System.out.println(startDate);
		List<?> results = session.createCriteria(FeedItem.class)
				.add(Restrictions.ge("pubDate", startDate)).list();
		return (FeedItem[]) results.toArray(new FeedItem[0]);
	}

	public static void saveFeedItems(List<FeedItem> feedItems, Feed feed)
			throws RssWebServiceException {
		for (FeedItem feedItem : feedItems) {
			FeedItem existingFeedItem = feed.findFeedItem(feedItem);
			if (existingFeedItem != null) {
				if (existingFeedItem.getPubDate().compareTo(
						feedItem.getPubDate()) < 0) {
					feedItem.setId(existingFeedItem.getId());
					updateFeedItem(feedItem);
				}
			} else {
				feedItem.setFeed(feed);
				insertFeedItem(feedItem);
			}
		}
	}
}
