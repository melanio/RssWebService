package reyes.melanio.portfolio.rsswebservice.data;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;

public class Feed {

	private Long id;
	private URL url;
	private Set<FeedItem> feedItems;

	public Feed() {

	}

	public Feed(String URL) throws MalformedURLException {
		setUrlString(URL);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setURL(String URL) throws MalformedURLException {
		setUrlString(URL);
	}

	public URL getURL() {
		return url;
	}

	public void setFeedItems(Set<FeedItem> feedItems) {
		try {
			if (this.feedItems == null) {
				this.feedItems = feedItems;
			} else {
				this.feedItems.clear();
				this.feedItems.addAll(feedItems);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addFeedItems(Set<FeedItem> feedItems) {
		if (null == this.feedItems) {
			setFeedItems(feedItems);
		} else {
			this.feedItems.addAll(feedItems);
		}
	}

	@SuppressWarnings("unused")
	private void addFeedItem(FeedItem feedItem) {
		if (null == this.feedItems) {
			setFeedItems(new TreeSet<FeedItem>());
		}
		this.feedItems.add(feedItem);
	}

	public Set<FeedItem> getFeedItems() {
		return this.feedItems;
	}

	private void setUrlString(String URL) throws MalformedURLException {
		url = new URL(URL);
	}

	@SuppressWarnings("unused")
	private String getUrlString() {
		return url.toString();
	}

	public FeedItem findFeedItem(FeedItem feedItem) {
		FeedItem result = null;
		for (FeedItem exisitingItem : this.feedItems) {
			if (exisitingItem.getLink().equals(feedItem.getLink())) {
				result = exisitingItem;
			}
		}
		return result;

	}
}
