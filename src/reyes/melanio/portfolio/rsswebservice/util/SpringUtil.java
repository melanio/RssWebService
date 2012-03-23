package reyes.melanio.portfolio.rsswebservice.util;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import reyes.melanio.portfolio.rsswebservice.parser.FeedParserBucket;

public class SpringUtil {

	private static FeedParserBucket feedParserBucket = null;
	private static DateParser dateParser = null;

	public static FeedParserBucket getParserBucket() {
		if (SpringUtil.feedParserBucket == null) {
			XmlBeanFactory beanFactory = new XmlBeanFactory(
					new ClassPathResource("spring-cfg.xml"));

			SpringUtil.feedParserBucket = (FeedParserBucket) beanFactory
					.getBean("FeedParserBucketBean");
		}
		return SpringUtil.feedParserBucket;
	}

	public static DateParser getDateParser() {
		if (SpringUtil.dateParser == null) {
			XmlBeanFactory beanFactory = new XmlBeanFactory(
					new ClassPathResource("spring-cfg.xml"));

			SpringUtil.dateParser = (DateParser) beanFactory
					.getBean("DateParserBean");

		}
		return SpringUtil.dateParser;
	}

}
