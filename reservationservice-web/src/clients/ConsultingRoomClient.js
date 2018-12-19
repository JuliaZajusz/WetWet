import { client } from './RestClient'

export const getConsultingRooms = async () => {
  const response = await client.get(`/api/consultingRoom/all`);
  return response.data
};


export const saveConsultingRoom = async (consultingRoom) => {
  const response = await client.post(`/api/consultingRoom`, consultingRoom);
  return response.data
};

