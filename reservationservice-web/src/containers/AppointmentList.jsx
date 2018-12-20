import React, { Component } from 'react';
import { Table } from 'antd';


class AppointmentList extends Component {
  //
  // constructor() {
  //     super();
  //     this.state = {
  //         data : []
  //     }
  // }
  // componentWillMount(){
  //     getPatients().then(res => this.setState({data: res}))
  // }
  columns = [
    {
      title: 'Tytuł',
      dataIndex: 'title',
      key: 'title',
      // render: text => <a href="">{text}</a>
    }, {
      title: 'Opis',
      dataIndex: 'description',
      key: 'description',
    }, {
      title: 'Data',
      dataIndex: 'date',
      key: 'date',
    }
    , {
      title: 'Godzina',
      dataIndex: 'startTime',
      key: 'startTime',
    },
  ];


  render() {
    const data = this.props.data;
    return (
      <div className={'margin-top-md'}>
        <h3>Wizyty</h3>
        <Table dataSource={data}
               columns={this.columns}
               rowKey='id'
               size="medium"
          // onRowClick={(patron) => history.push('/patient/' + patron.id)}
        />
      </div>
    )
  }
}

export default AppointmentList
