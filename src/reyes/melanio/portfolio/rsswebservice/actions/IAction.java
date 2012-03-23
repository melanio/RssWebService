package reyes.melanio.portfolio.rsswebservice.actions;

public interface IAction {

	public abstract void execute();

	public WebServiceResponse getResponse();
}
