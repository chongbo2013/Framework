package org.domain.name.app;

interface IUserInfo {

        boolean isLogin();

        void login(String data);

        void logout();

        String getAuthorization();
}
