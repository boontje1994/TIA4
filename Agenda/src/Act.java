import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Act {
	private String artist;
	private int popularity;
	private String stage;
	private String startTime;
	private Date startTimeDate;
	private String endTime;
	private Date endTimeDate;

	public Act(String artist, int pop, String stage, String startTime,
			String eindTime) {
		this.artist = artist;
		this.popularity = pop;
		this.stage = stage;
		this.startTime = startTime;
		this.startTimeDate = dateFromHourMinSec(startTime);
		this.endTime = eindTime;
		this.endTimeDate = dateFromHourMinSec(eindTime);

	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getStartTimeDate() {
		return this.startTimeDate;

	}

	public Date getEndTimeDate() {
		return this.endTimeDate;

	}

	private Date dateFromHourMinSec(final String hhmmss) {
		final String[] hms = hhmmss.split(":");
		final GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hms[0]));
		gc.set(Calendar.MINUTE, Integer.parseInt(hms[1]));
		gc.set(Calendar.SECOND, Integer.parseInt(hms[2]));
		gc.set(Calendar.MILLISECOND, 0);
		return gc.getTime();

	}

	public boolean isNowPlaying() {
		final Date now = new Date();
		return now.after(getStartTimeDate()) && now.before(getStartTimeDate());
	}
	
	public String toString(){
		return artist + "\t" + stage + "\t" + startTime + " - " + endTime;
	}
	
	

}
