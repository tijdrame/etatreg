import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BduDepotComponent } from './bdu-depot.component';
import { BduDepotDetailComponent } from './bdu-depot-detail.component';
import { BduDepotUpdateComponent } from './bdu-depot-update.component';
import { BduDepotDeleteDialogComponent } from './bdu-depot-delete-dialog.component';
import { bduDepotRoute } from './bdu-depot.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bduDepotRoute)],
  declarations: [BduDepotComponent, BduDepotDetailComponent, BduDepotUpdateComponent, BduDepotDeleteDialogComponent],
  entryComponents: [BduDepotDeleteDialogComponent],
})
export class EtatregBduDepotModule {}
