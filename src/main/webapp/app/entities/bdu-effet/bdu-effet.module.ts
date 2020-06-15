import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BduEffetComponent } from './bdu-effet.component';
import { BduEffetDetailComponent } from './bdu-effet-detail.component';
import { BduEffetUpdateComponent } from './bdu-effet-update.component';
import { BduEffetDeleteDialogComponent } from './bdu-effet-delete-dialog.component';
import { bduEffetRoute } from './bdu-effet.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bduEffetRoute)],
  declarations: [BduEffetComponent, BduEffetDetailComponent, BduEffetUpdateComponent, BduEffetDeleteDialogComponent],
  entryComponents: [BduEffetDeleteDialogComponent],
})
export class EtatregBduEffetModule {}
