import { client } from './RestClient';

export const getEmployeesList = async () => {
  const response = await client.get(`/api/employee/all`);
  return response.data

  // return [{
  //   id: 1,
  //   firstName: 'Jan',
  //   lastName: 'Kowalski',
  //   position: 'Recepcjonista',
  // },
  //   {
  //     id: 2,
  //     firstName: 'Jan',
  //     lastName: 'Kowalski',
  //     position: 'Recepcjonista',
  //   },
  //   {
  //     id: 3,
  //     firstName: 'Jan',
  //     lastName: 'Kowalski',
  //     position: 'Recepcjonista',
  //   }]
}
