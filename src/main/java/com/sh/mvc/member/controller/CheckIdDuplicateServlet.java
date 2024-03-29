package com.sh.mvc.member.controller;

import com.google.gson.Gson;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/member/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet
{
    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CheckIdDuplicateServlet 아이디 중복 체크 서블릿");
        //1.사용자 입력값 처리
        String id = req.getParameter("id");
        System.out.println(id);

        //2.업무로직
        Member member = memberService.findById(id);
        System.out.println("아이디중복 확인 findby id:"+member);
        boolean result = member==null;

        //3.응답 json 작성..
        resp.setContentType("application/json; charset=utf-8");
        Map<String,Object> map = Map.of("result",result);

        new Gson().toJson(map,resp.getWriter()); //인자,출력스트림

    }
}