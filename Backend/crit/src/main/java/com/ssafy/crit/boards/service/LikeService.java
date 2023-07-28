package com.ssafy.crit.boards.service;


import com.ssafy.crit.auth.entity.User;
import com.ssafy.crit.boards.entity.Board;
import com.ssafy.crit.boards.entity.LikeTable;
import com.ssafy.crit.boards.repository.LikeRepository;
import com.ssafy.crit.imsimember.entity.Member;
import com.ssafy.crit.imsimember.repository.MemberRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikeRepository likeRepository;
	private final MemberRepository memberRepository;

	public LikeDto like(User user , Board board) {
		memberRepository.findByName(user.getId()).orElseThrow();
		if (likeRepository.findByUserAndBoard(user, board).isEmpty()) {
			LikeTable like = new LikeTable();
			like.setUser(user);
			like.setBoard(board);
			likeRepository.save(like);
		}

		return new LikeDto(board.getTitle(), user.getId());
	}

	public LikeDto unlike(User user, Board board) {
		likeRepository.deleteByUserAndBoard(user, board);
		return new LikeDto(board.getTitle(), user.getId());
	}
}


