import { client } from './RestClient'

// export const getConsultingRooms = async () => {
//   const response = await client.get(`/api/consultingRoom/all`);
//   return response.data
// };

export const getConsultingRooms = async (body) => {

    const response = await client.post(`/api/consultingRoom/all`, body);
    return response.data
};

export const getConsultingRoom = async (id) => {
  const response = await client.get(`/api/consultingRoom/${id}`);
  return response.data
};

export const getConsultingRoomInaccessibility = async (id) => {
  const response = await client.get(`/api/consultingRoomInnaccessibility/${id}`);
  return response.data
};


export const getConsultingRoomsInaccessibilities = async () => {
  const response = await client.get(`/api/consultingRoomInnaccessibility/all`);
  return response.data
};


export const saveConsultingRoom = async (consultingRoom) => {
  const response = await client.post(`/api/consultingRoom`, consultingRoom);
  return response.data
};

export const saveConsultingRoomInaccessibility = async (consultingRoomInaccessibility) => {
  const response = await client.post(`/api/consultingRoomInnaccessibility`, consultingRoomInaccessibility);
  return response.data
};




