import React from "react";
import logo from "./logo.svg";
import styles from "./App.module.css";
import { TextField, Button } from "@material-ui/core";
import uuid from "react-uuid";
import BootstrapTable from "react-bootstrap-table-next";
import axios from "axios";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import "bootstrap/dist/css/bootstrap.min.css";

const columns = [
  {
    dataField: "id",
    text: "ID",
    headerStyle: () => {
      return { width: "10%" };
    }
  },
  {
    dataField: "question",
    text: "Question"
  },
  {
    dataField: "answer",
    text: "Answer"
  }
];

class App extends React.Component {
  state = {
    quesList: [],
    ques: "",
    ans: "",
    content: "Answer Questions",
    mode: 0,
    index: 0,
    tot: 0,
    quesAsked: "No Ques in DB",
    ansGiven: ""
  };

  componentDidMount() {
    axios.get("http://localhost:8080/questions/getAllQuestions").then(res => {
      console.log(res.data);
      this.setState({ quesList: res.data, tot: res.data.length });
    });

    if (this.state.tot != 0) {
      this.setState({
        quesAsked: this.state.quesList[this.state.index].question
      });
    }
  }

  handleQuesChange = e => {
    this.setState({ ques: e.target.value });
  };

  handleAnsChange = e => {
    this.setState({ ans: e.target.value });
  };

  handleInputChange = e => {
    this.setState({ ansGiven: e.target.value });
  };

  handleNextQues = () => {
    this.setState({ index: this.state.index + 1 });
  };

  handleValidateAnswer = () => {
    if (this.state.ansGiven == this.state.quesList[this.state.index].answer) {
      alert("correct ans");
    } else {
      alert("wrong ans");
    }
  };

  handleSwitchButtonClick = () => {
    if (this.state.mode == 0) {
      this.setState({ mode: 1, content: "Add Questions", index: 0 });
    } else if (this.state.mode == 1) {
      this.setState({ mode: 0, content: "Answer Questions" });
    }
  };

  handleAddButtonClick = () => {
    if (this.state.ques == "") {
      alert("please enter a question");
      return;
    }
    if (this.state.ans == "") {
      alert("please enter an answer");
      return;
    }
    let tup = {
      question: this.state.ques,
      answer: this.state.ans
    };
    axios
      .post("http://localhost:8080/questions/postQuestion", tup)
      .then(res => {
        axios
          .get("http://localhost:8080/questions/getAllQuestions")
          .then(res => {
            console.log(res.data);
            this.setState({ quesList: res.data, tot: res.data.length });
          });
      })
      .catch(err => {
        alert("some error occurred please try again");
      });
  };

  render() {
    let content = null;
    let Ques = null;
    let validate = null;

    if (this.state.index >= this.state.tot) {
      Ques = "No More Ques Available";
    } else {
      Ques = this.state.quesList[this.state.index].question;
      validate = (
        <Button
          variant="outlined"
          className={styles.button}
          onClick={this.handleValidateAnswer}
        >
          Validate Answer
        </Button>
      );
    }

    if (this.state.mode == 0) {
      content = (
        <div>
          <div className={styles.tableContainer}>
            <BootstrapTable
              keyField="id"
              data={this.state.quesList}
              columns={columns}
            />
          </div>
          <div className={styles.inputContainer}>
            <TextField
              className={styles.textField}
              // style={{width: '40%'}}
              value={this.state.ques}
              onChange={this.handleQuesChange}
              id="question-input"
              label="Question"
              multiline
              rows="4"
              variant="outlined"
            />
            <TextField
              className={styles.textField}
              value={this.state.ans}
              onChange={this.handleAnsChange}
              id="answer-input"
              label="Answer"
              multiline
              rows="4"
              variant="outlined"
            />
            <Button
              variant="outlined"
              color="primary"
              className={styles.addButton}
              onClick={this.handleAddButtonClick}
            >
              Add
            </Button>
          </div>
        </div>
      );
    } else {
      content = (
        <div className={styles.rootContainer}>
          <div className={styles.rowFlex}>
            <div className={styles.title}>Question : </div>
            <div className={styles.text}>{Ques}</div>
          </div>
          <div className={styles.rowFlex}>
            <div className={styles.title}>Answer : </div>
            <TextField
              className={styles.textField}
              // style={{width: '40%'}}
              value={this.state.ansGiven}
              onChange={this.handleInputChange}
              id="quiz-answer"
              rows="1"
              variant="outlined"
            />
          </div>
          <div
            className={styles.rowFlex}
            style={{ justifyContent: "flex-end" }}
          >
            {validate}
            <Button
              variant="outlined"
              className={styles.button}
              onClick={this.handleNextQues}
            >
              Next Question
            </Button>
          </div>
        </div>
      );
    }

    return (
      <div className={styles.rootContainer}>
        <Button
          className={styles.switchButton}
          onClick={this.handleSwitchButtonClick}
          variant="contained"
          color="primary"
        >
          {this.state.content}
        </Button>
        {content}
      </div>
    );
  }
}

export default App;
