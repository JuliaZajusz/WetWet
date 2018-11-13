import React, { Component } from 'react';
import BigCalendar from 'react-big-calendar-like-google';
import moment from 'moment';
import 'moment/locale/pl'
import { getAppointments } from '../clients/AppointmentClient'


class Timetable extends Component {
  allViews = Object.keys(BigCalendar.Views).map(k => BigCalendar.Views[k])
  state = { events: [] }

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
        this.setState({ events: newEvents })
      },
    )
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
          onSelectEvent={event => alert(event.title)}
          onSelectSlot={(slotInfo) => alert(
            `selected slot: \n\nstart ${slotInfo.start.toLocaleString()} ` +
            `\nend: ${slotInfo.end.toLocaleString()}` +
            `\naction: ${slotInfo.action}`,
          )}

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

      </div>
    )
  }

}

export default Timetable
