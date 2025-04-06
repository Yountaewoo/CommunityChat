// app/login/page.tsx
"use client";

import { useEffect } from "react";
import Head from "next/head";
import Script from "next/script";
import { createClient } from "@supabase/supabase-js";

export default function LoginPage() {
    useEffect(() => {
        const supabaseUrl = process.env.NEXT_PUBLIC_SUPABASE_URL!;
        const supabaseKey = process.env.NEXT_PUBLIC_SUPABASE_KEY!;
        const client = createClient(supabaseUrl, supabaseKey);

        // 로그인 상태에 따른 버튼 표시 업데이트 함수
        async function checkLogin() {
            const { data } = await client.auth.getSession();
            const session = data.session;
            const loginBtn = document.querySelector("#login") as HTMLElement;
            const logoutBtn = document.querySelector("#logout") as HTMLElement;
            if (session === null) {
                loginBtn.style.display = "inline-block";
                logoutBtn.style.display = "none";
            } else {
                loginBtn.style.display = "none";
                logoutBtn.style.display = "inline-block";
            }
        }

        // GitHub OAuth 로그인 함수
        async function signInWithGithub() {
            const { data, error } = await client.auth.signInWithOAuth({
                provider: "github",
                options: {
                    redirectTo: "http://localhost:3000/login", // 로그인 성공 후 이동할 주소
                },
            });
            if (error) {
                console.error("OAuth 로그인 에러:", error);
            } else {
                console.log("OAuth 로그인 데이터:", data);
            }
        }

        // 로그아웃 함수
        async function signOut() {
            const { error } = await client.auth.signOut();
            if (error) {
                console.error("Logout 에러:", error);
            }
            checkLogin();
        }

        // 버튼 요소 가져오기 및 이벤트 리스너 등록
        const loginButton = document.getElementById("login");
        const logoutButton = document.getElementById("logout");

        if (loginButton) {
            loginButton.addEventListener("click", signInWithGithub);
        }
        if (logoutButton) {
            logoutButton.addEventListener("click", signOut);
        }

        // 초기 로그인 상태 체크
        checkLogin();

        // 컴포넌트 언마운트 시 이벤트 클린업
        return () => {
            if (loginButton) {
                loginButton.removeEventListener("click", signInWithGithub);
            }
            if (logoutButton) {
                logoutButton.removeEventListener("click", signOut);
            }
        };
    }, []);

    return (
        <>
            <Head>
                <title>Login - Supabase Auth</title>
                <meta charSet="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
            </Head>
            <Script
                src="https://cdn.jsdelivr.net/npm/@supabase/supabase-js@2"
                strategy="beforeInteractive"
            />
            <main className="flex items-center justify-center flex-col min-h-screen bg-gray-100">
                <div className="bg-white p-10 rounded shadow-md text-center max-w-md w-full">
                    <h1 className="text-2xl font-bold text-gray-800 mb-5">Login</h1>
                    <p className="text-gray-700 font-semibold mb-8">
                        Sign in with GitHub to continue
                    </p>
                    <input
                        type="button"
                        value="Login with GitHub"
                        id="login"
                        className="bg-gray-800 text-white py-2 px-4 rounded mb-4 cursor-pointer"
                    />
                    <br />
                    <input
                        type="button"
                        value="Logout"
                        id="logout"
                        className="bg-red-500 text-white py-2 px-4 rounded cursor-pointer"
                        style={{ display: "none" }}
                    />
                </div>
            </main>
        </>
    );
}
