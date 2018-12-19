import React, { Component } from 'react';
import BigCalendar from 'react-big-calendar-like-google';
import moment from 'moment';
import 'moment/locale/pl'
import { deleteAppointment, getAppointments } from '../clients/AppointmentClient'
import { Modal } from 'antd';
import WrappedAppointmentForm from './AppointmentForm';
import { getConsultingRooms } from '../clients/ConsultingRoomClient'
import AppointmentCard from './AppointmentCard'


class Timetable extends Component {
  allViews = Object.keys(BigCalendar.Views).map(k => BigCalendar.Views[k])
  state = { events: [], consultingRooms: [], slotInfo: { action: null }, appointmentId: null }

  getRandomColor = () => {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

  componentWillMount() {
    moment.locale('pl-PL');
    BigCalendar.setLocalizer(
      BigCalendar.momentLocalizer(moment),
    );
    this.loadAppointments()
  }

  loadAppointments() {
    let appointments = {};
    let newEvents = [];
    getAppointments().then((res) => {
        appointments = res
        newEvents = Object.keys(appointments).map((key) => {
          let appointment = appointments[key]
          let start = [...appointment.date.split('-'), ...appointment.startTime.split(':')].map((str) => parseInt(str));
          let end = [...appointment.date.split('-'), ...appointment.endTime.split(':')].map((str) => parseInt(str));
          let event = {
            title: appointment.title,
            bgColor: this.getRandomColor(),
            start: new Date(start[0], start[1] - 1, start[2], start[3], start[4], start[5]),
            end: new Date(end[0], end[1] - 1, end[2], end[3], end[4], end[5]),
          }
          return event
        });
        this.setState({ events: newEvents, appointments: appointments })
      },
    )
  }

  getAppointment(slot) {
    let appointmentId = null;
    let matchingEvents = this.state.events
      .map((event, idx) => {
        return { id: idx, ...event }
      })
      .filter((event) => {
        return moment(slot.start).format('YYYY-MM-DD HH') === moment(event.start).format('YYYY-MM-DD HH')
      })
      .map((event) => event.id);
    appointmentId = ((this.state.appointmentId == matchingEvents[0]) && matchingEvents[1]) ? matchingEvents[1] : matchingEvents[0];
    return appointmentId;
  }

  showModal = (slotInfo) => {
        this.setState({
          appointmentId: this.getAppointment(slotInfo),
            visible: true,
            slotInfo: slotInfo,
        });
    getConsultingRooms().then((res) => this.setState({ consultingRooms: res }))
    }

    handleOk = (e) => {
        this.setState({
            visible: false,
          edit: false,
        });
      this.loadAppointments()
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
    return (
      <div className={'margin-md'}>
        <BigCalendar
            selectable
            events={this.state.events}
            defaultView='week'
            views={this.allViews}
            scrollToTime={new Date(1970, 1, 1, 6)}
            defaultDate={Date.now()}
            onSelectSlot={(slotInfo) => this.showModal(slotInfo)}
          // onSelectEvent={event => alert(event.title)}
          // onDoubleClickEvent={(a)=> console.log(a)}
          // onSelecting={(a)=> console.log('onSelecting', a)}

            messages={
            {
              date: 'Data',
              time: 'Czas',
              event: 'Wydarzenie',
              allDay: 'Cały dzień',
              week: 'Tydzień',
              work_week: 'Tydzień pracujący',
              day: 'Dzień',
              month: 'Miesiąc',
              previous: 'Poprzedni',
              next: 'Następny',
              yesterday: 'Wczoraj',
              tomorrow: 'Jutro',
              today: 'Dzisiaj',
              agenda: 'Agenda',

              noEventsInRange: 'Nie ma wydarzeń w tym okresie.',

              showMore: total => `+${total} więcej`,
            }
          }
        />
        {this.state.slotInfo.action === 'click' && this.state.appointmentId ?
          <Modal
            title="Wizyta"
            visible={this.state.visible}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            footer={[]}
          >
            <AppointmentCard
              edit={this.state.edit}
              appointment={this.state.appointments[this.state.appointmentId]}
              slotInfo={this.state.slotInfo}
              onOk={this.handleOk}
              onCancel={this.handleCancel}
              onDelete={this.handleDelete}
              consultingRooms={this.state.consultingRooms}
              onEnableEdit={() => this.setState({ edit: true })}
            />
          </Modal>
          :
          <Modal
            title="Umawianie wizyty"
            visible={this.state.visible}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
            footer={[]}
          >
            <WrappedAppointmentForm
              consultingRooms={this.state.consultingRooms}
              slotInfo={this.state.slotInfo}
              onOk={this.handleOk}
              onCancel={this.handleCancel}/>
          </Modal>
        }

      </div>
    )
  }

}

export default Timetable
