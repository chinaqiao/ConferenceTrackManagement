package algorithms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 计划安排
 * @author Eagle
 */
public class ConferenceTrackManagement {
	
	private static List<Plan> list = new ArrayList<Plan>(){{
		add(new Plan("Writing Fast Tests Against Enterprise Rails 60min", 60));
		add(new Plan("Overdoing it in Python 45min", 45));
		add(new Plan("Lua for the Masses 30min", 30));
		add(new Plan("Ruby Errors from Mismatched Gem Versions 45min", 45));
		add(new Plan("Common Ruby Errors 45min", 45));
		add(new Plan("Rails for Python Developers lightning", 5));
		add(new Plan("Communicating Over Distance 60min", 60));
		add(new Plan("Accounting-Driven Development 45min", 45));
		add(new Plan("Accounting-Driven Development 45min", 30));
		add(new Plan("Sit Down and Write 30min", 30));
		add(new Plan("Pair Programming vs Noise 45min", 45));
		add(new Plan("Rails Magic 60min", 60));
		add(new Plan("Ruby on Rails: Why We Should Move On 60min", 60));
		add(new Plan("Clojure Ate Scala (on my project) 45min", 45));
		add(new Plan("Programming in the Boondocks of Seattle 30min", 30));
		add(new Plan("Ruby vs. Clojure for Back-End Development 30min", 30));
		add(new Plan("Ruby on Rails Legacy App Maintenance 60min", 60));
		add(new Plan("A World Without HackerNews 30min", 30));
		add(new Plan("User Interface CSS in Rails Apps 30min", 30));
	}};
	
	public static void main(String[] args) {
		
		int total = 0;
		for(Plan p : list){
			total += p.time;
		}
		System.out.println("会议总占用时长：" + total);

		Track track1AM = new Track("第一天 上午", 180, 9); 
		Track track1PM = new Track("第一天 下午", 240, 13);
		Track track2AM = new Track("第二天 上午", 180, 9); 
		Track track2PM = new Track("第二天 下午", 240, 13);
		List<Track> trackList = new ArrayList<Track>(){{
			add(track1AM);
			add(track1PM);
			add(track2AM);
			add(track2PM);
		}};
		
		for(int k = 0; k < list.size(); k++){
			Plan plan = list.get(k);
			System.out.println("开始安排:" + plan);
			for(int i = 0; i < trackList.size(); i++){
				Track t = trackList.get(i);
				System.out.print("检查: " + t.title + "  结果：");
				if (t.add(plan)){
					System.out.println("安排成功 !");
					break;
				}else{
					System.out.println("时间超了.");
				}
			}
		}
		
		trackList.forEach(System.out::println);
	}
	
	/**
	 * 会议
	 * @author Eagle
	 */
	static class Track {
		private static SimpleDateFormat format = new SimpleDateFormat("a HH:mm  ");
		private String title ;
		private int totalTime;
		private int currentTime;
		private List<Plan> list;
		private boolean isFull;
		private Calendar startTime;
		/**
		 * 
		 * @param title 标题
		 * @param time 最大时长
		 * @param startHOUR	开始时间，24小时制
		 */
		public Track(String title, int time, int startHOUR) {
			this.currentTime = 0;
			this.title = title;
			this.totalTime = time;
			this.list = new ArrayList<Plan>();

			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, startHOUR);
			c.set(Calendar.MINUTE, 0);
			this.startTime = c;
			
		}
		public boolean add(Plan p) {
			if(this.isFull) return false;
			if(this.currentTime + p.time <= this.totalTime) {
				this.list.add(p);
				this.currentTime += p.time;
				if(this.currentTime == this.totalTime) {
					this.isFull = true;
				}
				return true;
			}
			return false;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("");
			sb.append(this.title + '\n');
			this.list.forEach(plan -> {
				sb.append(format.format(startTime.getTime()) +  plan.title + '\n');
				startTime.add(Calendar.MINUTE, plan.time);
			});
			return sb.toString();
		}
		
		public boolean isFull() {
			return isFull;
		}
		public void setFull(boolean isFull) {
			this.isFull = isFull;
		}
		public int getCurrentTime() {
			return currentTime;
		}
		public void setCurrentTime(int currentTime) {
			this.currentTime = currentTime;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getTotalTime() {
			return totalTime;
		}
		public void setTotalTime(int totalTime) {
			this.totalTime = totalTime;
		}
		public List<Plan> getList() {
			return list;
		}
		public void setList(List<Plan> list) {
			this.list = list;
		}
		
	}

	/**
	 * 会议类
	 * @author Eagle
	 *
	 */
	static class Plan{
		/**
		 * 会议标题
		 */
		private String title;
		/**
		 * 会议耗时
		 */
		private int time;
		
		private boolean isPick = false; 
		
		/**
		 * 会议内容
		 * @param title 标题
		 * @param time 时长
		 */
		public Plan(String title, int time) {
			this.setTitle(title);
			this.setTime(time);
		}
		
		@Override
		public String toString() {
			return "时长：" + time + ", 名称:" + title;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}
	}
}
