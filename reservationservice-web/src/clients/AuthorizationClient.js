import { client } from './RestClient'
import axios from 'axios'
import history from '../history'
import jwt_decode from 'jwt-decode';

export const setToken = (token) => {
  localStorage.setItem('TOKEN', token)
  setHeaders(token)
}

export const getToken = () => {
  setHeaders(localStorage.getItem('TOKEN'))
}

export const setHeaders = (token) => {
  axios.defaults.headers.common['Authorization'] = 'Bearer ' + token
}

export const signOut = () => {
  localStorage.removeItem('TOKEN');
  history.push('/login')
}

client.interceptors.response.use(null, function (err) {
  if (err.response.status === 401) {
    signOut()
  }
  return Promise.reject(err)
})


export const createAccount = async (user) => {
  return client.post(`/authorization/sign-up`, user)
}

export const logIn = async (credentials) => {
  const response = await client.post(`/authorization/login`, credentials)
  setToken(response.data.accessToken)
  return response;
}

export const resetPassword = async (credentials) => {
  const response = await client.post(`/authorization/resetPassword`, credentials)
  return response;
}

export const getPositionFromToken = () => {
  let token = localStorage.getItem('TOKEN');
  let positionId = token ? jwt_decode(token).user.employee.positionId : null;
  return client.get(`/api/position/${positionId}`);
}
