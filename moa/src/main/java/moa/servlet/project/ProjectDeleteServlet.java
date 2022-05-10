package moa.servlet.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moa.beans.ProjectAttachDao;
import moa.beans.ProjectDao;

@WebServlet(urlPatterns = "/project/delete.do")
public class ProjectDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int projectNo = Integer.parseInt(req.getParameter("projectNo"));
			
			//프로젝트 삭제
			ProjectDao projectDao = new ProjectDao();
			boolean delProject = projectDao.delete(projectNo);
			
			//첨부파일 삭제
			ProjectAttachDao projectAttachDao = new ProjectAttachDao();
			boolean delAttach = projectAttachDao.delete(projectNo);
			
			boolean success =  delProject&&delAttach;
			System.out.println(success);
			System.out.println("성공");
			if(success) {
				resp.sendRedirect(req.getContextPath()+"/admin/projectList.jsp");
			}
			else {
				resp.sendError(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
