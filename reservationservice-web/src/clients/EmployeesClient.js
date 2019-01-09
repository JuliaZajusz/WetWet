import { client } from './RestClient';

export const getEmployeesList = async () => {
  const response = await client.get(`/api/employee/all`);
  return response.data
}

export const getEmployees = async (body) => {

    const response = await client.post(`/api/employee/all`, body);
    return response.data
};

export const getEmployee = async (id) => {
  const response = await client.get(`/api/employee/${id}`);
  return response.data
};


export const getEmployeeAvailability = async (id) => {
    const response = await client.get(`/api/consultingRoomInnaccessibility/${id}`);
    return response.data
};


export const getEmployeeAvailabilities = async () => {
    const response = await client.get(`/api/consultingRoomInnaccessibility/all`);
    return response.data
};


export const saveEmployee = async (employee) => {
    const response = await client.post(`/api/employee`, employee);
    return response.data
};

export const saveEmployeeAvailability = async (employeeAvailability) => {
    const response = await client.post(`/api/employeeAvailability`, employeeAvailability);
    return response.data
};
