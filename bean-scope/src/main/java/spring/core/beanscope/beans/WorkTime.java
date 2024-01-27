package spring.core.beanscope.beans;

import java.time.LocalDateTime;

//It is going to be configure as Session scope. As work time is always for a session.
public class WorkTime {
	private LocalDateTime inTime = LocalDateTime.now();
	private LocalDateTime outTime ;
	
	public LocalDateTime getInTime() {
		return inTime;
	}
	public void setInTime(LocalDateTime inTime) {
		this.inTime = inTime;
	}
	public LocalDateTime getOutTime() {
		return outTime;
	}
	public void setOutTime(LocalDateTime outTime) {
		this.outTime = outTime;
	}

}
