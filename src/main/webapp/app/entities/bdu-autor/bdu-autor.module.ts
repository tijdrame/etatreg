import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BduAutorComponent } from './bdu-autor.component';
import { BduAutorDetailComponent } from './bdu-autor-detail.component';
import { BduAutorUpdateComponent } from './bdu-autor-update.component';
import { BduAutorDeleteDialogComponent } from './bdu-autor-delete-dialog.component';
import { bduAutorRoute } from './bdu-autor.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bduAutorRoute)],
  declarations: [BduAutorComponent, BduAutorDetailComponent, BduAutorUpdateComponent, BduAutorDeleteDialogComponent],
  entryComponents: [BduAutorDeleteDialogComponent],
})
export class EtatregBduAutorModule {}
