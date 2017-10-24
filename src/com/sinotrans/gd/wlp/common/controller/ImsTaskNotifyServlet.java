package com.sinotrans.gd.wlp.common.controller;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.agvInterface.ImsCallBack;
import com.sinotrans.gd.wlp.common.agvInterface.ImsNotifyParam;
import com.sinotrans.gd.wlp.common.service.ReqTaskManager;
import com.sinotrans.gd.wlp.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/8/17.
 */
@WebServlet(name = "imsInboundAccount", urlPatterns = {"/imsInboundAccount/accountIntoOrder"})
public class ImsTaskNotifyServlet extends HttpServlet {

    private Log log = LogFactory.getLog(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OutputStream ps = null;
        ImsCallBack<String> response_xml_content = new ImsCallBack<String>();
        try {
            ReqTaskManager reqTaskManager = ContextUtils.getBeanOfType(ReqTaskManager.class);
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
            if(StringUtils.isEmpty(sb.toString())){
                response_xml_content.setMessage("接收内容不能为空");
                response_xml_content.setCode("99");
            }else{
//                request.get
                String value = sb.toString();
//                System.out.println("数据流内容: "+value);
                ImsNotifyParam imsNotifyParam = (ImsNotifyParam) JsonUtil.jsonToBean(value, ImsNotifyParam.class);
                response_xml_content = reqTaskManager.imsReqTaskNotify(imsNotifyParam);
            }
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            ps = response.getOutputStream();
            //这句话的意思，使得放入流的数据是utf8格式
            ps.write(JsonUtil.beanToJson(response_xml_content).getBytes("UTF-8"));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            ps.flush();
            ps.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
