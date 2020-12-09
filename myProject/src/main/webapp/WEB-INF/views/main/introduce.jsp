<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/introduce_slider.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/introduce_index.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/introduce_style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/introduce.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet" />

    <title>Document</title>
</head>
<body id="introduce">
    <header>
        <div class="header-wrpa">
            <div id="logo">
                <a href="/">
                    <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="에브리미닛">
                </a>
                <p>
                    <span class="name multiple">EVERYMINUTE</span>
                    <span class="subname">이젠대학교</span>
                </p>
            </div>
            <div id="Member">
                <a href="${pageContext.request.contextPath}/member/memberLogin.do">로그인</a>
                <a href="${pageContext.request.contextPath}/member/memberRegister.do">회원가입</a>
                <input type="hidden" id="userUserid" value="p470109">
                <input type="hidden" id="userSchool" value="185">
                <input type="hidden" id="userCampus" value="217">
              </div>
        </div>

    </header>
        <div id="slider">
                <div class="wrap">
                    <div class="line">
                    <div class="left">
                        <div class="content">
                        <span class="spanSlow">EveryMinute</span>
                        </div>
                    </div><!--
                    --><div class="right">
                        <div class="content">
                        <span class="spanSlow">EveryMinute</span>
                        </div>
                    </div>
                    </div>
                    <div class="line">
                    <div class="left">
                        <div class="content">
                        <span class="spanSlow">UNIVERSITY</span>
                        </div>
                    </div><!--
                    --><div class="right">
                        <div class="content">
                        <span class="spanSlow">UNIVERSITY</span>
                        </div>
                    </div>
                    </div>
                    <div class="line">
                        <div class="left">
                            <div class="content">
                            <span class="spanFast">SERVICE</span>
                            </div>
                        </div><!--
                        --><div class="right">
                            <div class="content">
                            <span class="spanFast">SERVICE</span>
                            </div>
                        </div>
                        </div>
                        <div class="line">
                            <div class="left">
                            <div class="content">
                                <span class="spanSlow">WEB</span>
                            </div>
                            </div><!--
                            --><div class="right">
                            <div class="content">
                                <span class="spanSlow">WEB</span>
                            </div>
                            </div>
                        </div>
                </div> 
        </div>
    <div id="overflow">
        <section class="content_text">
            <div class="fade_up_1" data-aos="fade-up">
                <h2>350만 대학생을 위한<br>
                <strong>국내 1위 대학생 서비스 에브리미닛!</strong>
                </h2>
                <div class="paragraph">
                    <div class="fade_up_1" data-aos="fade-up">
                        <p>시간표 작성, 수업 일정 및 할일 등 편리한 <strong>학업 관리</strong>가 가능하고,<br>학식 등 유용한 <strong>학교 생활 정보</strong>를 접할 수 있으며,<br>같은 캠퍼스의 학생들과 소통하는 <strong>익명 커뮤니티</strong>를 이용할 수 있습니다.</p>
                    </div>
                </div>
            </div>
                <div class="figures">
                    <div class="fade_up_1" data-aos="fade-up">
                        <div>
                            <p class="number"><strong data-number="2365">2,365</strong><span>만</span></p>
                            <p class="description">만들어진 시간표</p>
                        </div>
                            <hr>
                        <div>
                            <p class="number"><strong data-number="268">268</strong><span>만</span></p>
                            <p class="description">강의평/시험정보</p>
                        </div>
                            <hr>
                        <div>
                            <p class="number"><strong data-number="129">129</strong><span>만</span></p>
                            <p class="description">중고 거래된 책</p>
                        </div>
                            <hr>
                        <div>
                            <p class="number"><strong data-number="8">8</strong><span>억</span><strong data-number="7097">7,097</strong><span>만</span></p>
                            <p class="description">작성된 게시물</p>
                    </div>
                </div>
            </div>
            <div data-aos="fade-up">
                <section class="content_text2">
                    <div class="fade_up_1" data-aos="fade-up">
                        <h2>전국 396개 캠퍼스<br>
                        <strong>재학생 커뮤니티 에브리미닛!</strong>
                        </h2>
                        <div class="paragraph">
                            <div class="fade_up_1" data-aos="fade-up">
                                <p>학교 인증을 거친 재학생들의 안전한 대화를 위한 <strong>익명 시스템</strong>과<br>학생들이 직접 게시판을 개설하여 운영하는 <strong>커뮤니티 플랫폼</strong>을 통해<br>많은 대학교에서 가장 활발히 이용하는 재학생 커뮤니티로 자리잡았습니다.</p>
                            </div>
                        </div>
                    </div>
                        <div class="figures">
                            <div class="fade_up_1" data-aos="fade-up">
                                <div>
                                    <div class="fade_up_1" data-aos="zoom-in">
                                        <p class="icon"><img src="https://everytime.kr/images/about/icon.authorized.png"></p>
                                        <p class="description">철저한 학교 인증</p>
                                    </div>
                                </div>
                                <div>
                                    <div class="fade_up_1" data-aos="zoom-in">
                                        <p class="icon"><img src="https://everytime.kr/images/about/icon.anonymous.png"></p>
                                        <p class="description">완벽한 익명 시스템</p>
                                    </div>
                                </div>
                                <div>
                                    <div class="fade_up_1" data-aos="zoom-in">
                                        <p class="icon"><img src="https://everytime.kr/images/about/icon.users.png"></p>
                                        <p class="description">재학생 운영 게시판</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <section class="footer white">
                            <ul class="links">
                            <li><a href="/page/serviceagreement">이용약관</a></li>
                            <li><a href="/page/privacy">개인정보처리방침</a></li>
                            <li><a href="/page/faq">문의하기</a></li>
                            <li class="copyright"><a href="/">© 에브리타임</a></li>
                            </ul>
                        </section>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                            <div class="aa1" data-aos="fade-in"></div>
                        </div>
                </section>
            </div>
    </div>
      
      <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
      <script>
          AOS.init();
      </script>
</body>
</html>