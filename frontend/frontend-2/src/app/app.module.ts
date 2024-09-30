import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KnowledgesComponent } from './knowledges/knowledges.component';
import {DatePipe, NgForOf} from "@angular/common";
import {KnowledgeService} from "./knowledge.service";
import {FormsModule} from "@angular/forms";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import { EditDialogComponent } from './edit-dialog/edit-dialog.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCard} from "@angular/material/card";
import { HomeComponent } from './home/home.component';
import {MatInput} from "@angular/material/input";

@NgModule({
  declarations: [
    AppComponent,
    KnowledgesComponent,
    EditDialogComponent,
    HomeComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    DatePipe,
    NgForOf,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCard,
    MatInput
  ],
  providers: [KnowledgeService, provideHttpClient(withInterceptorsFromDi()), provideAnimationsAsync()],
  bootstrap: [AppComponent]
})
export class AppModule { }

// $clipboard {provide:LocationStrategy, useClass: HashLocationStrategy}
