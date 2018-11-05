import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { getHello } from './clients/HelloClient'

class App extends Component {
  constructor(props) {
    super(props)
    this.state = {
      text: 'tu pojawi się tekst jeśli klikniesz przycisk'
    }
  }

  getText() {
    getHello().then((res) => {
      this.setState({text: res})})
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
          <br/>
          <button onClick={() => this.getText()}>get text</button>
          <div>{this.state.text}</div>
        </header>
      </div>
    );
  }
}

export default App;
