package com.takefive.plugins.jira.wechat.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Date utilities for simple time manipulations
 *
 */
public class DateUtils {

  interface CalendarManipulator {
    /**
     * Operates on a {@link Calendar} instance. First argument is the instance on which
     * the function is called.
     * 
     * @param calendar
     * @param field
     * @param amount
     */
    public void apply(Calendar calendar, int field, int amount);
  }
  
  public static class HasOffset {
    private final Calendar calendar;
    
    private final int offset;
    
    private final CalendarManipulator action;
    
    protected HasOffset(int offset, CalendarManipulator action) {
      calendar = Calendar.getInstance();
      this.offset = offset;
      this.action = action;
    }
    
    public Date hour() {
      return apply(Calendar.HOUR);
    }
    
    public Date minutes() {
      return apply(Calendar.MINUTE);
    }
    
    public Date seconds() {
      return apply(Calendar.SECOND);
    }
    
    private Date apply(int field) {
      Calendar newTime = (Calendar) calendar.clone();
      action.apply(newTime, field, offset);
      return newTime.getTime();
    }
  }

  public static HasOffset after(int amount) {
    return new HasOffset(amount, Calendar::add);
  }
  
  public static HasOffset before(int amount) {
    return new HasOffset(-amount, Calendar::add);
  }
  
  public static HasOffset set(int amount) {
    return new HasOffset(amount, Calendar::set);
  }
  
  public static HasOffset rollForwards(int amount) {
    return new HasOffset(amount, Calendar::roll);
  }
  
  public static HasOffset rollBackwards(int amount) {
    return new HasOffset(-amount, Calendar::roll);
  }
}
