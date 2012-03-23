package reyes.melanio.portfolio.rsswebservice.data;

import java.text.DateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class FeedItem {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String link;
	private String content;
	private Date pubDate;
	private Feed feed;

	public FeedItem() {

	}

	public FeedItem(Feed feed) {
		setFeed(feed);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getLink() {
		return this.link;
	}

	public String getContent() {
		return this.content;
	}

	public Date getPubDate() {
		return this.pubDate;
	}

	public Feed getFeed() {
		return this.feed;
	}

	@Override
	public String toString() {
		return "title: " + title + " link: " + link + " content: " + content
				+ " pubDate: "
				+ DateFormat.getDateTimeInstance().format(pubDate);
	}
}
