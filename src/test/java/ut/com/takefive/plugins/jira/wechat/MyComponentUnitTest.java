package ut.com.takefive.plugins.jira.wechat;

import org.junit.Test;
import com.takefive.plugins.jira.wechat.MyPluginComponent;
import com.takefive.plugins.jira.wechat.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}