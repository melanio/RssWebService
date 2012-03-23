package reyes.melanio.portfolio.rsswebservice.data.hibernate;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import reyes.melanio.portfolio.rsswebservice.RssWebServiceException;
import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.error.Errors;

public class FeedDao {
	private static Logger logger = Logger.getLogger(FeedDao.class);

	public FeedDao() {

	}

	private static Session getSession() {
		return SessionFactoryUtil.instance().getCurrentSession();
	}

	public static Long insertFeed(Feed feed) throws RssWebServiceException {
		Transaction trans = null;
		Session session = getSession();
		try {
			trans = session.beginTransaction();
			session.save(feed);
			session.flush();
			trans.commit();
		} catch (RuntimeException e) {
			if (trans != null && trans.isActive()) {
				try {
					logger.error(
							"Error interting Feed, rolling back transaction", e);
					trans.rollback();
				} catch (HibernateException he) {
					logger.error("Error rolling back transaction", he);
				}
			}
			e.printStackTrace();
			throw new RssWebServiceException(Errors.DATABASE_INSERT_ERROR,
					Errors.DATABASE_INSERT_ERROR_TEXT, e);

		}
		return feed.getId();
	}

	public static void deleteFeed(Feed feed) throws RssWebServiceException {
		Transaction trans = null;
		Session session = getSession();
		try {
			trans = session.beginTransaction();
			session.delete(feed);
			trans.commit();

		} catch (RuntimeException e) {
			if (trans != null && trans.isActive()) {
				try {
					logger.error(
							"Error deleting Feed, rolling back transaction", e);
					trans.rollback();
				} catch (HibernateException he) {
					logger.error("Error rolling back transaction", he);
				}
			}
			throw new RssWebServiceException(Errors.DATABASE_DELETE_ERROR,
					Errors.DATABASE_DELETE_ERROR_TEXT, e);
		}
	}

	public static void updateFeed(Feed feed) throws RssWebServiceException {
		Transaction trans = null;
		Session session = getSession();
		try {
			trans = session.beginTransaction();
			Feed retrievedFeed = findById(feed.getId());
			retrievedFeed.setURL(feed.getURL().toString());
			trans.commit();
		} catch (RuntimeException e) {
			if (trans != null && trans.isActive()) {
				try {
					logger.error(
							"Error updating Feed rolling back transaction", e);
					trans.rollback();
				} catch (HibernateException he) {
					logger.error("Error rolling back transaction", he);
				}
			}
			throw new RssWebServiceException(Errors.DATABASE_UPDATE_ERROR,
					Errors.DATABASE_UPDATE_ERROR_TEXT, e);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// Should not reach here.
		}
	}

	@SuppressWarnings({ "unused" })
	public static Feed findById(Long id) {
		Feed feed = null;
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		feed = (Feed) session.get(Feed.class, id);
		return feed;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public static Feed findByUrl(String url) {
		Feed feed = null;
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		List<Feed> results = session.createCriteria(Feed.class)
				.add(Restrictions.eq("urlString", url)).list();
		if (results.size() > 0) {
			feed = (Feed) results.get(0);
		}
		return feed;
	}

}
