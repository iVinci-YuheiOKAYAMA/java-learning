package bean;

public class Member {
	private String id;
	private String task;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}

	public Member(String id, String task) {
		this.id = id;
		this.task = task;
	}
}
