package moa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moa.beans.ProjectDao;

@WebFilter(filterName = "f21-project_coming", urlPatterns = "/project/project_coming.jsp")
public class ProjectComingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		try {
			int projectNo = Integer.parseInt(request.getParameter("projectNo"));
			
			ProjectDao projectDao = new ProjectDao();
			int check = projectDao.checkProjectSchedule(projectNo);
			
			if(check == 1){
				resp.sendRedirect(req.getContextPath()+"/project/project_detail.jsp?projectNo="+projectNo);
			}else if(check == 2){
				resp.sendRedirect(req.getContextPath()+"/project/project_cloing.jsp?projectNo="+projectNo);
			}else {
				chain.doFilter(request, response);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
