import React, { Component } from 'react';
import { Modal, Table } from 'antd';
import AppointmentCard from './AppointmentCard'
import { deleteAppointment, getAppointment } from '../clients/AppointmentClient'
import { getPatient } from '../clients/PatientClient'
import { getPatron } from '../clients/PatronClient'
import { getConsultingRooms } from '../clients/ConsultingRoomClient'
import { getEmployee, getEmployeesList } from '../clients/EmployeesClient'


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
      patient: null,
      patron: null,
      employee: null
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

  showModal = (appointment) => {
    console.log(appointment);
    // let appointment;
    //   getAppointment(this.state.appointments[rowId].id).then((res)=>
    //   appointment = res
    //   )
      getPatient(appointment.patientId).then((res) =>
        this.setState({patient: res})
      )
      getPatron(appointment.patronId).then((res) =>
        this.setState({patron: res})
      )
    getEmployee(appointment.employeeId).then((res) =>
      this.setState({employee: res})
    )
    // console.log(appointment)
    this.setState({ appointment: appointment, visible: true })
    getConsultingRooms().then((res) => this.setState({ consultingRooms: res }))
    getEmployeesList().then((res) => this.setState({ employees: res }))
  }

  handleOk = (e) => {
    this.setState({
      visible: false,
      appointment: null,
      patient: null,
      patron: null,
      edit: false,
    });
    this.props.onReloadAppointments()
  }

  handleCancel = (e) => {
    this.setState({
      visible: false,
      appointment: null,
      patient: null,
      patron: null,
      edit: false,
    });
  }

  // handleOk = (e) => {
  //   this.setState({
  //     visible: false,
  //     edit: false,
  //   });
  //   this.props.onReloadAppointments()
  // }
  //
  // handleCancel = (e) => {
  //   this.setState({
  //     visible: false,
  //     edit: false,
  //   });
  // }

  handleDelete = () => {
    deleteAppointment(this.state.appointment.id)
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
                 this.showModal(appointment)
                 // console.log(appointment)
                 // this.setState({ appointment: appointment, visible: true })
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
            patient={this.state.patient}
            patron={this.state.patron}
            employee={this.state.employee}
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
