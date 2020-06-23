import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bank-infos',
        loadChildren: () => import('./bank-infos/bank-infos.module').then(m => m.EtatregBankInfosModule),
      },
      {
        path: 'files-infos',
        loadChildren: () => import('./files-infos/files-infos.module').then(m => m.EtatregFilesInfosModule),
      },
      {
        path: 'acheteur-vendeur',
        loadChildren: () => import('./acheteur-vendeur/acheteur-vendeur.module').then(m => m.EtatregAcheteurVendeurModule),
      },
      {
        path: 'nature-debiteur',
        loadChildren: () => import('./nature-debiteur/nature-debiteur.module').then(m => m.EtatregNatureDebiteurModule),
      },
      {
        path: 'nature-depot',
        loadChildren: () => import('./nature-depot/nature-depot.module').then(m => m.EtatregNatureDepotModule),
      },
      {
        path: 'objet-credit',
        loadChildren: () => import('./objet-credit/objet-credit.module').then(m => m.EtatregObjetCreditModule),
      },
      {
        path: 'secteur-activite',
        loadChildren: () => import('./secteur-activite/secteur-activite.module').then(m => m.EtatregSecteurActiviteModule),
      },
      {
        path: 'bp-2-infos',
        loadChildren: () => import('./bp-2-infos/bp-2-infos.module').then(m => m.EtatregBp2InfosModule),
      },
      {
        path: 'bp-3-infos',
        loadChildren: () => import('./bp-3-infos/bp-3-infos.module').then(m => m.EtatregBp3InfosModule),
      },
      {
        path: 'bp-4-infos',
        loadChildren: () => import('./bp-4-infos/bp-4-infos.module').then(m => m.EtatregBp4InfosModule),
      },
      {
        path: 'bdu-cdb',
        loadChildren: () => import('./bdu-cdb/bdu-cdb.module').then(m => m.EtatregBduCdbModule),
      },
      {
        path: 'bdu-depot',
        loadChildren: () => import('./bdu-depot/bdu-depot.module').then(m => m.EtatregBduDepotModule),
      },
      {
        path: 'bdu-effet',
        loadChildren: () => import('./bdu-effet/bdu-effet.module').then(m => m.EtatregBduEffetModule),
      },
      {
        path: 'bdu-autor',
        loadChildren: () => import('./bdu-autor/bdu-autor.module').then(m => m.EtatregBduAutorModule),
      },
      {
        path: 'chargement',
        loadChildren: () => import('./chargement/chargement.module').then(m => m.EtatregChargementModule),
      },
      {
        path: 'dechargement',
        loadChildren: () => import('./dechargement/dechargement.module').then(m => m.EtatregDechargementModule),
      },
      {
        path: 'periode',
        loadChildren: () => import('./periode/periode.module').then(m => m.EtatregPeriodeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EtatregEntityModule {}
