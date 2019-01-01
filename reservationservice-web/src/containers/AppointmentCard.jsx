import { Button, DatePicker, Form, Select } from 'antd';
import React, { Component } from 'react';
import { Col, Row } from 'react-bootstrap'
import WrappedAppointmentForm from './AppointmentForm'
import { getAllConsultingRooms } from '../clients/ConsultingRoomClient'

const Option = Select.Option;
const FormItem = Form.Item;
const { MonthPicker, RangePicker } = DatePicker;

class AppointmentCard extends Component {
  constructor(props) {
    super();
    this.state = {
      consultingRooms: [],
    }
  }

  componentWillMount = () => {
    getAllConsultingRooms().then((res) => this.setState({ consultingRooms: res }));
  }

  format = 'HH:mm';


  render() {
    // let consultingRoom = this.props.consultingRooms.find((cr) => cr.id === this.props.appointment.consultingRoomId)
    let consultingRoom = this.state.consultingRooms.find((cr) => cr.id === this.props.appointment.consultingRoomId)
    return (
      <div>
        {this.props.edit ?
          <WrappedAppointmentForm
            patron={this.props.patron}
            employees={this.props.employees}
            appointment={this.props.appointment}
            consultingRooms={this.props.consultingRooms}
            slotInfo={this.props.slotInfo}
            onOk={this.props.onOk}
            onCancel={this.props.onCancel}/>
          :
          <div>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Opiekun</Col>
              <Col xs={'12'} sm={'8'}>{
                this.props.patron &&
                this.props.patron.firstName + ' ' + this.props.patron.lastName
              }</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Pacjent</Col>
              <Col xs={'12'} sm={'8'}>{
                this.props.patient &&
                this.props.patient.name + ' - ' + this.props.patient.breed.name + ' (' + this.props.patient.breed.species + ')'
              }</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Pracownik</Col>
              <Col xs={'12'} sm={'8'}>{
                this.props.employee &&
                this.props.employee.firstName + ' ' + this.props.employee.lastName
              }</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Tytuł</Col>
              <Col xs={'12'} sm={'8'}>{this.props.appointment.title}</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Opis</Col>
              <Col xs={'12'} sm={'8'}>{this.props.appointment.description}</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Data wizyty</Col>
              <Col xs={'12'} sm={'8'}>{this.props.appointment.date}</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Pocztątek wizyty</Col>
              <Col xs={'12'} sm={'8'}>{this.props.appointment.startTime}</Col>
            </Row>
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Koniec wizyty</Col>
              <Col xs={'12'} sm={'8'}>{this.props.appointment.endTime}</Col>
            </Row>
            {/*<Row className={'row'}>*/}
            {/*<Col xs={'12'} sm={'4'} className={'card-label'}>Adres</Col>*/}
            {/*<Col xs={'12'} sm={'8'}>TODO</Col>*/}
            {/*</Row>*/}
            <Row className={'row'}>
              <Col xs={'12'} sm={'4'} className={'card-label'}>Gabinet</Col>
              <Col xs={'12'}
                   sm={'8'}>{consultingRoom && consultingRoom.roomNumber + ' ' + consultingRoom.description}</Col>
            </Row>

            <div className={'button-container'}>
              <Button type="secondary" onClick={() => this.props.onDelete()}>Usuń</Button>
              <Button type="primary" onClick={() => this.props.onEnableEdit()}>Edytuj</Button>
            </div>
          </div>
        }
      </div>
    );
  }
}


export default AppointmentCard;
