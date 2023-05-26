import { apiInstance } from './index.js';

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`http://192.168.214.52/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers['access-token'] = sessionStorage.getItem('access-token');
  await api.get(`http://192.168.214.52/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers['refresh-token'] = sessionStorage.getItem('refresh-token'); //axios header에 refresh-token 셋팅
  await api.post(`http://192.168.214.52/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`http://192.168.214.52/user/logout/${userid}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout };
