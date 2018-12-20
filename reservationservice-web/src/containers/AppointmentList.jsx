import React, { Component } from 'react';
import { Modal, Table } from 'antd';
import AppointmentCard from './AppointmentCard'
import { deleteAppointment } from '../clients/AppointmentClient'


class AppointmentList extends Component {

  constructor(props) {
    super();
    this.state = {
      consultingRooms: [],
      appointment: null,
      employees: [],
      appointments: props.data,
      visible: false,
      edit: false,
    }

  }
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

  handleOk = (e) => {
    this.setState({
      visible: false,
      edit: false,
    });
    this.props.onReloadAppointments()
  }

  handleCancel = (e) => {
    this.setState({
      visible: false,
      edit: false,
    });
  }

  handleDelete = () => {
    deleteAppointment(this.state.appointments[this.state.appointmentId].id)
      .then(() => {
        this.handleOk();
      })
  }


  render() {
    const data = this.props.data;
    return (
      <div className={'margin-top-md'}>
        <h3>Wizyty</h3>
        <Table dataSource={data}
               columns={this.columns}
               rowKey='id'
               size="medium"
               onRowClick={(appointment) => {
                 console.log(appointment)
                 this.setState({ appointment: appointment, visible: true })
               }}
        />
        {this.state.appointment && <Modal
          title="Wizyta"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
          footer={[]}
        >
          <AppointmentCard
            edit={this.state.edit}
            employees={this.state.employees}
            appointment={this.state.appointment}
            slotInfo={null}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            onDelete={this.handleDelete}
            consultingRooms={this.state.consultingRooms}
            onEnableEdit={() => this.setState({ edit: true })}
          />
        </Modal>}
      </div>
    )
  }
}

export default AppointmentList
