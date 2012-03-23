package reyes.melanio.portfolio.rsswebservice.actions;

import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedDao.findById;
import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedItemDao.findByFeedIdAndDateRange;
import static reyes.melanio.portfolio.rsswebservice.data.hibernate.FeedItemDao.findByFeedIdAndStartDate;

import java.util.Date;

import reyes.melanio.portfolio.rsswebservice.data.Feed;
import reyes.melanio.portfolio.rsswebservice.util.DateParser;
import reyes.melanio.portfolio.rsswebservice.util.SpringUtil;

public class RetriveFeedItemsByDateAction extends AbstractAction {

	private Feed feed;
	Date startDate = null;
	Date endDate = null;

	public RetriveFeedItemsByDateAction(Long feedId, String startDate,
			String endDate) {
		super();
		this.feed = findById(feedId);

		DateParser dateParser = SpringUtil.getDateParser();
		if (startDate != null) {
			this.startDate = dateParser.parse(startDate.trim());
		}

		if (endDate != null) {
			this.endDate = dateParser.parse(endDate);
		}
	}

	public void execute() {
		if (checkFeed(feed)) {
			if (this.endDate == null) {
				webServiceResponse.setFeedItems(findByFeedIdAndStartDate(
						feed.getId(), startDate));
			} else {
				webServiceResponse.setFeedItems(findByFeedIdAndDateRange(
						feed.getId(), startDate, endDate));
			}
		}
	}

}
