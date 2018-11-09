import { client } from './RestClient'

export const getHello = async () => {
  const response = await client.get(`/api`);
  return response.data
};
