package com.sinotrans.gd.wlp.util;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.directwebremoting.extend.Call;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.impl.DefaultRemoter;

/**
 * @author cj
 * 
 */
public class DWRRemoter extends DefaultRemoter {

	@Override
	public Reply execute(Call call) {
		Reply reply = super.execute(call);
		if (reply.getThrowable() != null) {
			Throwable ex = ExceptionUtils.getRootCause(reply.getThrowable());
			if (ex == null) {
				ex = reply.getThrowable();
			}
			return new Reply(reply.getCallId(), reply.getReply(), ex);
		}
		return reply;
	}

	@Override
	public String generateInterfaceScript(String scriptName, String path)
			throws SecurityException {
		String commonScript = super.generateInterfaceScript(scriptName, path);
		String syncScript = commonScript.replaceAll(" = function",
				"_sync = function").replaceAll("\\{\\n",
				"{\n  dwr.engine._async = false;\n").replaceAll("\\n\\}",
				"\n  dwr.engine._async = true;\n}");
		StringBuilder buffer = new StringBuilder();
		buffer.append(commonScript);
		buffer.append(syncScript);
		return buffer.toString();
	}

}
