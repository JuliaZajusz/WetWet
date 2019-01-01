import React, { Component } from 'react';
import { Button, Table } from 'antd';
import history from '../history'
import { getConsultingRoomsInaccessibilities } from '../clients/ConsultingRoomClient'

class ConsultingRoomInaccessibilityList extends Component {

  constructor() {
    super();
    this.state = {
      data: [],
    }
  }

  componentWillMount() {
    getConsultingRoomsInaccessibilities().then(res => this.setState({ data: res }))
  }

  columns = [{
    title: 'Numer gabinetu',
    dataIndex: 'consultingRoom.roomNumber',
    key: 'consultingRoom.roomNumber',
  }, {
    title: 'Opis',
    dataIndex: 'consultingRoom.description',
    key: 'consultingRoom.description',
  }
    , {
      title: 'Data',
      dataIndex: 'date',
      key: 'date',
    },
    {
      title: 'Początek',
      dataIndex: 'startTime',
      key: 'startTime',
    }
    ,
    {
      title: 'Koniec',
      dataIndex: 'endTime',
      key: 'endTime',
    },
  ];


  render() {
    const data = this.state.data;
    return (
      <div className={'margin-md'}>
        <div className={'flex-between'}>
          <h3>Lista niedostepności gabinetów</h3>
          <Button onClick={() => this.addConsultingRoomInaccessibility()}>Dodaj</Button>
        </div>
        <Table dataSource={data}
               columns={this.columns}
               rowKey='id'
               size="medium"
               onRowClick={(cri) => history.push('/consultingRoomInaccessibility/' + cri.id)}
        />
      </div>
    )
  }

  addConsultingRoomInaccessibility() {
    history.push('consultingRoomInaccessibility/new');
  }
}

export default ConsultingRoomInaccessibilityList
