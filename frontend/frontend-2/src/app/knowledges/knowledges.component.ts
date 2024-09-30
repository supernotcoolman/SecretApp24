import {Component, Directive, OnInit} from '@angular/core';
import { KnowledgeService } from '../knowledge.service';
import {Knowledge} from "../knowledge";
import {MatDialog} from "@angular/material/dialog";
import {EditDialogComponent} from "../edit-dialog/edit-dialog.component";


@Component({
  selector: 'app-knowledges', //<app-knowledges>
  templateUrl: './knowledges.component.html',
  styleUrls: ['./knowledges.component.css']
})
export class KnowledgesComponent implements OnInit{
  knowledges: any[] = [];
  isCollapsed: boolean[] = [];
  newKnowledge: Knowledge = { id: 0, title: '', author: '', content: '', createdDate: new Date(), lastModifiedDate: new Date() };


  constructor(private knowledgeService: KnowledgeService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.knowledgeService.getAllKnowledges().subscribe(data => {
      this.knowledges = data;
      this.isCollapsed = new Array(data.length).fill(true);
    });
  }
  toggleCollapse(index: number): void {
    this.isCollapsed[index] = !this.isCollapsed[index];
  }

  addKnowledge(): void {
    this.knowledgeService.createKnowledge(this.newKnowledge).subscribe(data => {
      this.knowledges.push(data);
      this.newKnowledge = data;
      this.isCollapsed.push(true);
    })
  }

  clearForm(): void {

    this.newKnowledge.title = '';
    this.newKnowledge.content = '';
    this.newKnowledge.author = '';
  }

  deleteKnowledge(id: number): void {
    this.knowledgeService.deleteKnowledge(id).subscribe(data => {
      this.knowledges = this.knowledges.filter(knowledge => knowledge.id !== id);
      this.isCollapsed.pop();
    })
  }

  openEditDialog(knowledge: Knowledge): void {
    const dialogRef = this.dialog.open(EditDialogComponent,{width: "250px", data:{...knowledge}});

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        if (knowledge.id != null) {
          this.knowledgeService.updateKnowledge(knowledge.id, result).subscribe(updatedKnowledge => {
            const index = this.knowledges.findIndex(k => k.id === knowledge.id);
            if (index !== -1){
              this.knowledges[index] = updatedKnowledge;
            }
          })
        }
      }
    })
  }

}
