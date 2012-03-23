package reyes.melanio.portfolio.rsswebservice;

import reyes.melanio.portfolio.rsswebservice.actions.DeleteFeedAction;
import reyes.melanio.portfolio.rsswebservice.actions.DigestFeedAction;
import reyes.melanio.portfolio.rsswebservice.actions.InsertFeedAction;
import reyes.melanio.portfolio.rsswebservice.actions.RetrieveAllFeedItemsAction;
import reyes.melanio.portfolio.rsswebservice.actions.RetriveFeedItemsByDateAction;
import reyes.melanio.portfolio.rsswebservice.actions.UpdateFeedAction;
import reyes.melanio.portfolio.rsswebservice.actions.WebServiceResponse;

public class RssWebService {

	public WebServiceResponse insertFeed(String url) {
		InsertFeedAction action = new InsertFeedAction(url.trim());
		action.execute();

		return action.getResponse();
	}

	public WebServiceResponse deleteFeed(Long feedId) {
		DeleteFeedAction action = new DeleteFeedAction(feedId);
		action.execute();

		return action.getResponse();

	}

	public WebServiceResponse updateFeed(Long feedId, String url) {
		UpdateFeedAction action = new UpdateFeedAction(feedId, url);
		action.execute();

		return action.getResponse();
	}

	public WebServiceResponse digestFeed(Long feedId) {
		DigestFeedAction action = new DigestFeedAction(feedId);
		action.execute();

		return action.getResponse();
	}

	public WebServiceResponse retrieveAllFeedItems(Long feedId) {
		RetrieveAllFeedItemsAction action = new RetrieveAllFeedItemsAction(
				feedId);
		action.execute();

		return action.getResponse();
	}

	public WebServiceResponse retrieveFeedItemsByDate(Long feedId,
			String startDate, String endDate) {
		RetriveFeedItemsByDateAction action = new RetriveFeedItemsByDateAction(
				feedId, startDate, endDate);

		action.execute();

		return action.getResponse();
	}

}