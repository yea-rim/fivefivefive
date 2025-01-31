package moa.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommunityReplyDao {
	public int getCommunityReplySeq() throws Exception {
		Connection con = JdbcUtils.getConnection();

		String sql = "select community_reply_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();

		int communityReplyNo = rs.getInt("nextval");

		con.close();

		return communityReplyNo;
	}

	public void insert(CommunityReplyDto communityReplyDto) throws Exception {
		Connection con = JdbcUtils.getConnection();

		String sql = "INSERT INTO community_reply(community_reply_no,community_no, community_member_no, community_reply_content) values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, communityReplyDto.getCommunityReplyNo());
		ps.setInt(2, communityReplyDto.getCommunityNo());
		ps.setInt(3, communityReplyDto.getCommunityMemberNo());
		ps.setString(4, communityReplyDto.getCommunityReplyContent());
		ps.execute();

		con.close();
	}
	
	public CommunityReplyDto selectOne(int communityReplyNo) throws Exception {
		Connection con = JdbcUtils.getConnection();
		
		String sql = "select * from community_reply where community_reply_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, communityReplyNo);
		ResultSet rs = ps.executeQuery();
		
		CommunityReplyDto communityReplyDto;
		if(rs.next()) {
			communityReplyDto = new CommunityReplyDto();
			communityReplyDto.setCommunityMemberNo(rs.getInt("community_member_no"));
		}
		else {
			communityReplyDto = null;
		}
		
		con.close();
		
		return communityReplyDto;
	}

	public List<CommunityReplyDto> selectAll(int communityNo) throws Exception {
		Connection con = JdbcUtils.getConnection();

		String sql = "SELECT * FROM COMMUNITY_REPLY WHERE community_no = ? order by community_reply_no asc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, communityNo);
		ResultSet rs = ps.executeQuery();

		List<CommunityReplyDto> list = new ArrayList<>();

		while (rs.next()) {
			CommunityReplyDto communityReplyDto = new CommunityReplyDto();
			communityReplyDto.setCommunityReplyNo(rs.getInt("community_reply_no"));
			communityReplyDto.setCommunityNo(rs.getInt("community_no"));
			communityReplyDto.setCommunityMemberNo(rs.getInt("community_member_no"));
			communityReplyDto.setCommunityReplyTime(rs.getDate("community_reply_time"));
			communityReplyDto.setCommunityReplyContent(rs.getString("community_reply_content"));

			list.add(communityReplyDto);
		}

		con.close();

		return list;
	}

	public boolean edit(int communityReplyNo, String communityReplyContent) throws Exception {
		Connection con = JdbcUtils.getConnection();

		String sql = "UPDATE community_reply SET COMMUNITY_REPLY_CONTENT = ? WHERE COMMUNITY_REPLY_NO = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, communityReplyContent);
		ps.setInt(2, communityReplyNo);
		int count = ps.executeUpdate();

		con.close();

		return count > 0;
	}

	public boolean delete(int communityReplyNo) throws Exception {
		Connection con = JdbcUtils.getConnection();
		
		String sql = "delete community_reply where community_reply_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, communityReplyNo);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count > 0;
	}
	
	// 게시글 삭제시 해당 게시글 댓글 삭제
	public boolean deleteCommunityReply(int communityNo) throws Exception {
		Connection con = JdbcUtils.getConnection();

		String sql = "delete community_reply where community_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, communityNo);
		int count = ps.executeUpdate();

		con.close();

		return count > 0;
	}
	
}
