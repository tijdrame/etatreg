import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { NatureDebiteurComponent } from './nature-debiteur.component';
import { NatureDebiteurDetailComponent } from './nature-debiteur-detail.component';
import { NatureDebiteurUpdateComponent } from './nature-debiteur-update.component';
import { NatureDebiteurDeleteDialogComponent } from './nature-debiteur-delete-dialog.component';
import { natureDebiteurRoute } from './nature-debiteur.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(natureDebiteurRoute)],
  declarations: [
    NatureDebiteurComponent,
    NatureDebiteurDetailComponent,
    NatureDebiteurUpdateComponent,
    NatureDebiteurDeleteDialogComponent,
  ],
  entryComponents: [NatureDebiteurDeleteDialogComponent],
})
export class EtatregNatureDebiteurModule {}
