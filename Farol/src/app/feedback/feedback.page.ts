import { Component, OnInit, ViewChild } from '@angular/core';
import { Chart } from 'chart.js'; 

@Component({
  selector: 'app-list',
  templateUrl: 'feedback.page.html',
  styleUrls: ['feedback.page.scss']
})

export class FeedBackPage implements OnInit {
  @ViewChild('barChart', { static: true }) barChart;  
  bars: any;
  colorArray: any;

  ionViewDidEnter() {
    this.createBarChart();
  }

  
  createBarChart() {
    this.bars = new Chart(this.barChart.nativeElement, {
      type: 'bar',
      data: {
        labels: ['S1', 'S2', 'S3', 'S4', 'S5', 'S6', 'S7', 'S8'],
        datasets: [{
          label: 'Viewers in millions',
          data: [2.5, 3.8, 5, 6.9, 6.9, 7.5, 10, 17],
          backgroundColor: 'rgb(38, 194, 129)', // array should have same number of elements as number of dataset
          borderColor: 'rgb(38, 194, 129)',// array should have same number of elements as number of dataset
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  }

  ngOnInit() {
  }
  // add back when alpha.4 is out
  // navigate(item) {
  //   this.router.navigate(['/list', JSON.stringify(item)]);
  // }
}
