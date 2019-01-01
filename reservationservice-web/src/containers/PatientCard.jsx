import React, { Component } from 'react';
import PatronForm from './PatronForm'
import history from '../history'
import Button from 'antd/es/button/button'
import { getPatient, getPatientAppointments } from '../clients/PatientClient'
import AppointmentList from './AppointmentList'
import PatientForm from './PatientForm'

class PatientCard extends Component {
  state = {
    patient: null,
    patientAppointments: null,
  };

  componentWillMount = () => {
    this.reloadAppointments();
  }

  reloadAppointments = () => {
    if (this.props.history.location.pathname !== '/patient/new') {
      let path = this.props.history.location.pathname.split('/')
      getPatient(path[2]).then((res) => this.setState({ patient: res }))
      getPatientAppointments(path[2]).then((res) => this.setState({ patientAppointments: res }))
    }
  }

  render() {
    let arrayPath = this.props.history.location.pathname.split('/');

    return (
      <div className={'margin-md'}>
        {this.props.history.location.pathname === '/patient/new' && <div>
          <PatronForm onSubmitSuccess={(res) => {
            this.setState({ patient: res });
            history.push('/patient/' + res.id)
          }}/>
        </div>}
        {(arrayPath[2] !== 'new' && arrayPath.length <= 3 && this.state.patient) && <div>
          <div className={'flex-between'}>
            <h3>{this.state.patient.name} - {this.state.patient.breed.name} ({this.state.patient.breed.species})</h3>
            <Button onClick={() => history.push(this.props.history.location.pathname + '/edit')}>Edytuj</Button>
          </div>
          <div>
            {this.state.patient.coat}
          </div>
          <div>
            {this.state.patient.specialCHaracters}
          </div>
          <div>
            {this.state.patient.birthdate}
          </div>
          <div>
            {this.state.patient.sex}
          </div>
          <div>
            Opiekunowie:
            {/*{this.state.patient.pets.map((pet) => {*/}
            {/*return <div>*/}
            {/*{pet.name}*/}
            {/*</div>*/}
            {/*})}*/}
          </div>
          {/*{this.state.patientAppointments && this.state.patientAppointments.map((appointment) => {*/}
          {/*return <div>*/}
          {/*<div>*/}
          {/*{appointment.date}*/}
          {/*</div>*/}

          {/*</div>*/}
          {/*})}*/}
          <AppointmentList onReloadAppointments={() => this.reloadAppointments()}
                           data={this.state.patientAppointments}/>
        </div>
        }


        {(arrayPath[1] === 'patient'
          && arrayPath[3] === 'edit') && <div>
          <PatientForm data={this.state.patient} onSubmitSuccess={(res) => {
            this.setState({ patient: res });
            history.push('/patient/' + res.id)
          }}/>
        </div>}

      </div>
    )
  }
}

export default PatientCard;
